/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.indicator.business;

import fr.paris.lutece.test.LuteceTestCase;


public class IndicatorBusinessTest extends LuteceTestCase
{
    private final static String INDKEY1 = "IndKey1";
    private final static String INDKEY2 = "IndKey2";
    private final static String LABEL1 = "Label1";
    private final static String LABEL2 = "Label2";
    private final static String DESCRIPTION1 = "Description1";
    private final static String DESCRIPTION2 = "Description2";
    private final static int INDVALUE1 = 1;
    private final static int INDVALUE2 = 2;
    private final static int INDTARGET1 = 1;
    private final static int INDTARGET2 = 2;
    private final static String HISTORYPERIOD1 = "HistoryPeriod1";
    private final static String HISTORYPERIOD2 = "HistoryPeriod2";

    public void testBusiness(  )
    {
        // Initialize an object
        Indicator indicator = new Indicator(  );
        indicator.setIndKey( INDKEY1 );
        indicator.setLabel( LABEL1 );
        indicator.setDescription( DESCRIPTION1 );
        indicator.setIndValue( INDVALUE1 );
        indicator.setIndTarget( INDTARGET1 );
        indicator.setHistoryPeriod( HISTORYPERIOD1 );

        // Create test
        IndicatorHome.create( indicator );

        Indicator indicatorStored = IndicatorHome.findByPrimaryKey( indicator.getId(  ) );
        assertEquals( indicatorStored.getIndKey(  ), indicator.getIndKey(  ) );
        assertEquals( indicatorStored.getLabel(  ), indicator.getLabel(  ) );
        assertEquals( indicatorStored.getDescription(  ), indicator.getDescription(  ) );
        assertEquals( indicatorStored.getValue(  ), indicator.getValue(  ) );
        assertEquals( indicatorStored.getIndTarget(  ), indicator.getIndTarget(  ) );
        assertEquals( indicatorStored.getHistoryPeriod(  ), indicator.getHistoryPeriod(  ) );

        // Update test
        indicator.setIndKey( INDKEY2 );
        indicator.setLabel( LABEL2 );
        indicator.setDescription( DESCRIPTION2 );
        indicator.setIndValue( INDVALUE2 );
        indicator.setIndTarget( INDTARGET2 );
        indicator.setHistoryPeriod( HISTORYPERIOD2 );
        IndicatorHome.update( indicator );
        indicatorStored = IndicatorHome.findByPrimaryKey( indicator.getId(  ) );
        assertEquals( indicatorStored.getIndKey(  ), indicator.getIndKey(  ) );
        assertEquals( indicatorStored.getLabel(  ), indicator.getLabel(  ) );
        assertEquals( indicatorStored.getDescription(  ), indicator.getDescription(  ) );
        assertEquals( indicatorStored.getValue(  ), indicator.getValue(  ) );
        assertEquals( indicatorStored.getIndTarget(  ), indicator.getIndTarget(  ) );
        assertEquals( indicatorStored.getHistoryPeriod(  ), indicator.getHistoryPeriod(  ) );

        // List test
        IndicatorHome.getIndicatorsList(  );

        // Delete test
        IndicatorHome.remove( indicator.getId(  ) );
        indicatorStored = IndicatorHome.findByPrimaryKey( indicator.getId(  ) );
        assertNull( indicatorStored );
    }
}
