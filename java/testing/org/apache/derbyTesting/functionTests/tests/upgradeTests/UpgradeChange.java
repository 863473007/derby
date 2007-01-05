/*

Derby - Class org.apache.derbyTesting.functionTests.tests.upgradeTests.UpgradeChange

Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/
package org.apache.derbyTesting.functionTests.tests.upgradeTests;

import org.apache.derbyTesting.junit.BaseJDBCTestCase;

/**
 * Abstract class to provide support for test fixtures for
 * upgrade change testing.
 *
 */
abstract class UpgradeChange extends BaseJDBCTestCase {
      
    /**
     * Thread local for the phase of the test set.
     * Contains an Integer object.
     */
    static ThreadLocal phase = new ThreadLocal();
    
    /**
     * Thread local for the old version of the engine.
     * Contains a int array with four entries corresponding
     * to the four part Derby number.
     */
    static ThreadLocal oldVersion = new ThreadLocal();
    
    /**
     * Phases in upgrade test
     */
    static final String[] PHASES =
    {"CREATE", "SOFT UPGRADE", "POST SOFT UPGRADE", "UPGRADE", "POST UPGRADE"};
    
    
    /**
     * Create a database with old version
     */
    static final int PH_CREATE = 0;
    /**
     * Perform soft upgrade with new version
     */
    static final int PH_SOFT_UPGRADE = 1;
    /**
     * Boot the database with old release after soft upgrade
     */
    static final int PH_POST_SOFT_UPGRADE = 2;
    /**
     * Perform hard upgrade with new version
     */
    
    static final int PH_HARD_UPGRADE = 3;
    /**
     * Boot the database with old release after hard upgrade
     */
    static final int PH_POST_HARD_UPGRADE = 4;
    
    public UpgradeChange(String name) {
        super(name);
    }

    /**
     * Get the phase of the upgrade sequence we are running.
     * One of PH_CREATE, PH_SOFT_UPGRADE, PH_POST_SOFT_UPGRADE,
     * PH_HARD_UPGRADE, PH_POST_HARD_UPGRADE.
     */
    final int getPhase()
    {
        return ((Integer) phase.get()).intValue();
    }
    
    /**
     * Get the major number of the old version being upgraded
     * from.
     */
    final int getOldMajor() {
        return ((int[]) oldVersion.get())[0];
    }
    
    /**
     * Get the minor number of the old version being upgraded
     * from.
     */    
    final int getOldMinor() {
        return ((int[]) oldVersion.get())[1];
    }
    
    /**
     * Return true if the old version is equal to or more
     * recent that the passed in major and minor version.
     */
    boolean oldAtLeast(int requiredMajor, int requiredMinor) 
    {
        if (getOldMajor() > requiredMajor)
            return true;
        if ((getOldMajor() == requiredMajor)
            && (getOldMinor() >= requiredMinor))
            return true;
        return false;
    } 
}
