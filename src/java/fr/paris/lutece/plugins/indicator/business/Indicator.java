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

import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;


/**
 * This is the business class for the object Indicator
 */
public class Indicator
{
    // Variables declarations 
    private int _nId;
    @NotEmpty( message = "#i18n{indicator.validation.indicator.IndKey.notEmpty}" )
    @Size( max = 50, message = "#i18n{indicator.validation.indicator.IndKey.size}" )
    private String _strIndKey;
    @NotEmpty( message = "#i18n{indicator.validation.indicator.Label.notEmpty}" )
    @Size( max = 50, message = "#i18n{indicator.validation.indicator.Label.size}" )
    private String _strLabel;
    @NotEmpty( message = "#i18n{indicator.validation.indicator.Description.notEmpty}" )
    @Size( max = 255, message = "#i18n{indicator.validation.indicator.Description.size}" )
    private String _strDescription;
    private int _nIndValue;
    private int _nIndTarget;
    @NotEmpty( message = "#i18n{indicator.validation.indicator.HistoryPeriod.notEmpty}" )
    @Size( max = 50, message = "#i18n{indicator.validation.indicator.HistoryPeriod.size}" )
    private String _strHistoryPeriod;

    /**
     * Returns the Id
     * @return The Id
     */
    public int getId(  )
    {
        return _nId;
    }

    /**
     * Sets the Id
     * @param nId The Id
     */
    public void setId( int nId )
    {
        _nId = nId;
    }

    /**
     * Returns the IndKey
     * @return The IndKey
     */
    public String getIndKey(  )
    {
        return _strIndKey;
    }

    /**
     * Sets the IndKey
     * @param strIndKey The IndKey
     */
    public void setIndKey( String strIndKey )
    {
        _strIndKey = strIndKey;
    }

    /**
     * Returns the Label
     * @return The Label
     */
    public String getLabel(  )
    {
        return _strLabel;
    }

    /**
     * Sets the Label
     * @param strLabel The Label
     */
    public void setLabel( String strLabel )
    {
        _strLabel = strLabel;
    }

    /**
     * Returns the Description
     * @return The Description
     */
    public String getDescription(  )
    {
        return _strDescription;
    }

    /**
     * Sets the Description
     * @param strDescription The Description
     */
    public void setDescription( String strDescription )
    {
        _strDescription = strDescription;
    }

    /**
     * Returns the IndValue
     * @return The IndValue
     */
    public int getValue(  )
    {
        return _nIndValue;
    }

    /**
     * Sets the IndValue
     * @param nIndValue The IndValue
     */
    public void setIndValue( int nIndValue )
    {
        _nIndValue = nIndValue;
    }

    /**
     * Returns the IndTarget
     * @return The IndTarget
     */
    public int getIndTarget(  )
    {
        return _nIndTarget;
    }

    /**
     * Sets the IndTarget
     * @param nIndTarget The IndTarget
     */
    public void setIndTarget( int nIndTarget )
    {
        _nIndTarget = nIndTarget;
    }

    /**
     * Returns the HistoryPeriod
     * @return The HistoryPeriod
     */
    public String getHistoryPeriod(  )
    {
        return _strHistoryPeriod;
    }

    /**
     * Sets the HistoryPeriod
     * @param strHistoryPeriod The HistoryPeriod
     */
    public void setHistoryPeriod( String strHistoryPeriod )
    {
        _strHistoryPeriod = strHistoryPeriod;
    }
}
