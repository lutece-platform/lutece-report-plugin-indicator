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

import fr.paris.lutece.plugins.indicator.business.Indicator;
import fr.paris.lutece.plugins.indicator.business.IndicatorHistory;
import fr.paris.lutece.plugins.indicator.business.IndicatorHistoryHome;
import fr.paris.lutece.plugins.indicator.business.IndicatorHome;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppLogService;

import java.util.List;


/**
 * Indicator Updater Service
 */
public class IndicatorUpdaterService
{
    private static List<IndicatorFetcher> _listFetchers;
    private static IndicatorUpdaterService _singleton;

    public static IndicatorUpdaterService instance(  )
    {
        if ( _singleton == null )
        {
            _singleton = new IndicatorUpdaterService(  );
            _singleton.registerFetchers(  );
        }

        return _singleton;
    }

    private void registerFetchers(  )
    {
        _listFetchers = SpringContextService.getBeansOfType( IndicatorFetcher.class );
        for( IndicatorFetcher fetcher : _listFetchers )
        {
            AppLogService.info( "New Indicator fetcher registered : " + fetcher.getName());
        }
    }

    public String doFetch(  )
    {
        StringBuilder sbLogs = new StringBuilder(  );

        for ( IndicatorFetcher fetcher : _listFetchers )
        {
            try
            {
                List<Indicator> listIndicators = fetcher.fetch(  );

                for ( Indicator indicator : listIndicators )
                {
                    IndicatorHome.updateValue( indicator );
                    sbLogs.append( "Indicator '" ).append( indicator.getIndKey(  ) ).append( "' updated. New value is : " )
                          .append( indicator.getValue(  ) ).append( "\n" );
                    
                    createHistory( indicator );
                }
            }
            catch ( Exception e )
            {
                AppLogService.error( "Error fetching indicator " + e.getMessage(  ), e );
            }
        }

        return sbLogs.toString(  );
    }

    private void createHistory(Indicator indicator)
    {
        Indicator indicatorFull = IndicatorHome.findByKey( indicator.getIndKey() );
        String strTimeCode = TimeCodeService.getCurrentTimeCode( indicatorFull.getHistoryPeriod() );

        IndicatorHistory history = IndicatorHistoryHome.findByPrimaryKey( indicator.getIndKey(), strTimeCode);
        if( history != null )
        {
            IndicatorHistoryHome.remove( indicator.getIndKey(), strTimeCode );
        }

        history = new IndicatorHistory();
        history.setIndKey( indicator.getIndKey() );
        history.setTimeCode( strTimeCode );
        history.setIndValue( indicator.getValue() );
        history.setIndTarget( indicator.getIndTarget() );
        IndicatorHistoryHome.create( history );
    }
}
