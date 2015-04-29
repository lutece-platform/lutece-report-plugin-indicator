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
package fr.paris.lutece.plugins.indicator.service;

import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.util.ReferenceList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * TimeCodeService
 */
public class TimeCodeService
{
    public static final String PERIOD_DAY = "D";
    public static final String PERIOD_WEEK = "W";
    public static final String PERIOD_MONTH = "M";

    private static final String FORMAT_DAY = "%04d-%02d-%02d";
    private static final String FORMAT_WEEK = "%04d-W%02d";
    private static final String FORMAT_MONTH = "%04d-%02d";
    
    private static final String KEY_I18N_DAY = "indicator.period_label.day";
    private static final String KEY_I18N_WEEK = "indicator.period_label.week";
    private static final String KEY_I18N_MONTH = "indicator.period_label.month";
    
    private static final String INVALID_TIME_CODE = "000000";

    /**
     * Build a time code for the current date
     * @param strPeriod The Period to define the timecode format 
     * @return The time code
     */
    public static String getCurrentTimeCode( String strPeriod )
    {
        String strTimeCode = INVALID_TIME_CODE;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime( new Date() );
        int nYear = cal.get(Calendar.YEAR);
        int nMonth = cal.get(Calendar.MONTH) + 1;
        int nDay = cal.get( Calendar.DAY_OF_MONTH );
        int nWeek = cal.get(Calendar.WEEK_OF_YEAR);

        if( strPeriod.equalsIgnoreCase( PERIOD_DAY ))
        {
            strTimeCode = String.format( FORMAT_DAY , nYear , nMonth , nDay );
        }
        else if( strPeriod.equalsIgnoreCase( PERIOD_WEEK ))
        {
            strTimeCode = String.format( FORMAT_WEEK , nYear , nWeek );
        }
        else if( strPeriod.equalsIgnoreCase( PERIOD_MONTH ))
        {
            strTimeCode = String.format( FORMAT_MONTH , nYear , nMonth );
        }
        return strTimeCode;
    }
    
    /**
     * Returns a list of all available periods
     * @param locale The Locale
     * @return The list
     */
    public static ReferenceList getPeriod( Locale locale )
    {
        ReferenceList list = new ReferenceList();
        
        list.addItem( PERIOD_DAY, I18nService.getLocalizedString( KEY_I18N_DAY , locale));
        list.addItem( PERIOD_WEEK, I18nService.getLocalizedString( KEY_I18N_WEEK , locale));
        list.addItem( PERIOD_MONTH, I18nService.getLocalizedString( KEY_I18N_MONTH , locale));
        
        return list;
        
    }
    
}
