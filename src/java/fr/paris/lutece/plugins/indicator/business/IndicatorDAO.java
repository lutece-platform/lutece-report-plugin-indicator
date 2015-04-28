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
 * This class provides Data Access methods for Indicator objects
 */
public final class IndicatorDAO implements IIndicatorDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK = "SELECT max( id_indicator ) FROM indicator_key";
    private static final String SQL_QUERY_SELECT = "SELECT id_indicator, ind_key, label, description, ind_value, ind_target, history_period FROM indicator_key WHERE id_indicator = ?";
    private static final String SQL_QUERY_INSERT = "INSERT INTO indicator_key ( id_indicator, ind_key, label, description, ind_value, ind_target, history_period ) VALUES ( ?, ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM indicator_key WHERE id_indicator = ? ";
    private static final String SQL_QUERY_UPDATE = "UPDATE indicator_key SET id_indicator = ?, ind_key = ?, label = ?, description = ?, ind_value = ?, ind_target = ?, history_period = ? WHERE id_indicator = ?";
    private static final String SQL_QUERY_UPDATE_VALUE = "UPDATE indicator_key SET ind_value = ? WHERE ind_key = ?";
    private static final String SQL_QUERY_SELECTALL = "SELECT id_indicator, ind_key, label, description, ind_value, ind_target, history_period FROM indicator_key";

    /**
     * Generates a new primary key
     * @param plugin The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery(  );

        int nKey = 1;

        if ( daoUtil.next(  ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free(  );

        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( Indicator indicator, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        indicator.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, indicator.getId(  ) );
        daoUtil.setString( 2, indicator.getIndKey(  ) );
        daoUtil.setString( 3, indicator.getLabel(  ) );
        daoUtil.setString( 4, indicator.getDescription(  ) );
        daoUtil.setInt( 5, indicator.getValue(  ) );
        daoUtil.setInt( 6, indicator.getIndTarget(  ) );
        daoUtil.setString( 7, indicator.getHistoryPeriod(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Indicator load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery(  );

        Indicator indicator = null;

        if ( daoUtil.next(  ) )
        {
            indicator = new Indicator(  );
            indicator.setId( daoUtil.getInt( 1 ) );
            indicator.setIndKey( daoUtil.getString( 2 ) );
            indicator.setLabel( daoUtil.getString( 3 ) );
            indicator.setDescription( daoUtil.getString( 4 ) );
            indicator.setIndValue( daoUtil.getInt( 5 ) );
            indicator.setIndTarget( daoUtil.getInt( 6 ) );
            indicator.setHistoryPeriod( daoUtil.getString( 7 ) );
        }

        daoUtil.free(  );

        return indicator;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( Indicator indicator, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setInt( 1, indicator.getId(  ) );
        daoUtil.setString( 2, indicator.getIndKey(  ) );
        daoUtil.setString( 3, indicator.getLabel(  ) );
        daoUtil.setString( 4, indicator.getDescription(  ) );
        daoUtil.setInt( 5, indicator.getValue(  ) );
        daoUtil.setInt( 6, indicator.getIndTarget(  ) );
        daoUtil.setString( 7, indicator.getHistoryPeriod(  ) );
        daoUtil.setInt( 8, indicator.getId(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Collection<Indicator> selectIndicatorsList( Plugin plugin )
    {
        Collection<Indicator> indicatorList = new ArrayList<Indicator>(  );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            Indicator indicator = new Indicator(  );

            indicator.setId( daoUtil.getInt( 1 ) );
            indicator.setIndKey( daoUtil.getString( 2 ) );
            indicator.setLabel( daoUtil.getString( 3 ) );
            indicator.setDescription( daoUtil.getString( 4 ) );
            indicator.setIndValue( daoUtil.getInt( 5 ) );
            indicator.setIndTarget( daoUtil.getInt( 6 ) );
            indicator.setHistoryPeriod( daoUtil.getString( 7 ) );

            indicatorList.add( indicator );
        }

        daoUtil.free(  );

        return indicatorList;
    }

    @Override
    public void updateValue( String strKey, int nValue, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE_VALUE, plugin );

        daoUtil.setInt( 1, nValue );
        daoUtil.setString( 2, strKey );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }
}
