/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *         and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *         and the following disclaimer in the documentation and/or other materials
 *         provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *         contributors may be used to endorse or promote products derived from
 *         this software without specific prior written permission.
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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.Collection;


/**
 * This class provides Data Access methods for IndicatorHistory objects
 */
public final class IndicatorHistoryDAO implements IIndicatorHistoryDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT ind_key, time_code, ind_value, ind_target FROM indicator_history WHERE ind_key = ? AND time_code = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO indicator_history ( ind_key, time_code, ind_value, ind_target ) VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM indicator_history WHERE ind_key = ? AND time_code = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE indicator_history SET ind_key = ?, time_code = ?, ind_value = ?, ind_target = ? WHERE ind_key = ? AND time_code = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT ind_key, time_code, ind_value, ind_target FROM indicator_history WHERE ind_key = ?";

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( IndicatorHistory indicatorHistory, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        daoUtil.setString( 1, indicatorHistory.getIndKey(  ) );
        daoUtil.setString( 2, indicatorHistory.getTimeCode(  ) );
        daoUtil.setInt( 3, indicatorHistory.getIndValue(  ) );
        daoUtil.setInt( 4, indicatorHistory.getIndTarget(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public IndicatorHistory load( String strKey, String strTimeCode, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setString( 1, strKey );
        daoUtil.setString( 2, strTimeCode );
        daoUtil.executeQuery(  );

        IndicatorHistory indicatorHistory = null;

        if ( daoUtil.next(  ) )
        {
            indicatorHistory = new IndicatorHistory(  );
            indicatorHistory.setIndKey( daoUtil.getString( 1 ) );
            indicatorHistory.setTimeCode( daoUtil.getString( 2 ) );
            indicatorHistory.setIndValue( daoUtil.getInt( 3 ) );
            indicatorHistory.setIndTarget( daoUtil.getInt( 4 ) );
        }

        daoUtil.free(  );

        return indicatorHistory;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( String strKey, String strTimeCode, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setString( 1, strKey );
        daoUtil.setString( 2, strTimeCode );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( IndicatorHistory indicatorHistory, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setString( 1, indicatorHistory.getIndKey(  ) );
        daoUtil.setString( 2, indicatorHistory.getTimeCode(  ) );
        daoUtil.setInt( 3, indicatorHistory.getIndValue(  ) );
        daoUtil.setInt( 4, indicatorHistory.getIndTarget(  ) );
        daoUtil.setString( 5, indicatorHistory.getIndKey() );
        daoUtil.setString( 6, indicatorHistory.getTimeCode() );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Collection<IndicatorHistory> selectIndicatorHistorysList( String strKey, Plugin plugin )
    {
        Collection<IndicatorHistory> indicatorHistoryList = new ArrayList<IndicatorHistory>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.setString( 1, strKey );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            IndicatorHistory indicatorHistory = new IndicatorHistory(  );

            indicatorHistory.setIndKey( daoUtil.getString( 1 ) );
            indicatorHistory.setTimeCode( daoUtil.getString( 2 ) );
            indicatorHistory.setIndValue( daoUtil.getInt( 3 ) );
            indicatorHistory.setIndTarget( daoUtil.getInt( 4 ) );

            indicatorHistoryList.add( indicatorHistory );
        }

        daoUtil.free(  );

        return indicatorHistoryList;
    }
}
