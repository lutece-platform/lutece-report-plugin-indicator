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


public class IndicatorHistoryBusinessTest extends LuteceTestCase
{
    private final static String INDKEY1 = "IndKey1";
    private final static String TIMECODE1 = "001";
    private final static int INDVALUE1 = 1;
    private final static int INDVALUE2 = 2;
    private final static int INDTARGET1 = 1;
    private final static int INDTARGET2 = 2;

    public void testBusiness(  )
    {
        // Initialize an object
        IndicatorHistory history = new IndicatorHistory(  );
        history.setIndKey( INDKEY1 );
        history.setTimeCode( TIMECODE1 );
        history.setIndValue( INDVALUE1 );
        history.setIndTarget( INDTARGET1 );

        // Create test
        IndicatorHistoryHome.create( history );

        IndicatorHistory historyStored = IndicatorHistoryHome.findByPrimaryKey( history.getIndKey(  ),
                history.getTimeCode(  ) );
        assertEquals( historyStored.getIndKey(  ), history.getIndKey(  ) );
        assertEquals( historyStored.getTimeCode(  ), history.getTimeCode(  ) );
        assertEquals( historyStored.getIndValue(  ), history.getIndValue(  ) );
        assertEquals( historyStored.getIndTarget(  ), history.getIndTarget(  ) );

        // Update test
        history.setIndValue( INDVALUE2 );
        history.setIndTarget( INDTARGET2 );
        IndicatorHistoryHome.update( history );
        historyStored = IndicatorHistoryHome.findByPrimaryKey( history.getIndKey(  ), history.getTimeCode(  ) );
        assertEquals( historyStored.getIndKey(  ), history.getIndKey(  ) );
        assertEquals( historyStored.getTimeCode(  ), history.getTimeCode(  ) );
        assertEquals( historyStored.getIndValue(  ), history.getIndValue(  ) );
        assertEquals( historyStored.getIndTarget(  ), history.getIndTarget(  ) );

        // List test
        IndicatorHistoryHome.getIndicatorHistorysList( INDKEY1 );

        // Delete test
        IndicatorHistoryHome.remove( history.getIndKey(  ), history.getTimeCode(  ) );
        historyStored = IndicatorHistoryHome.findByPrimaryKey( history.getIndKey(  ), history.getTimeCode(  ) );
        assertNull( historyStored );
    }
}
