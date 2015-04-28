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
 * This is the business class for the object IndicatorHistory
 */
public class IndicatorHistory
{
    // Variables declarations 
    @NotEmpty( message = "#i18n{indicator.validation.history.IndKey.notEmpty}" )
    @Size( max = 50, message = "#i18n{indicator.validation.history.IndKey.size}" )
    private String _strIndKey;
    private String _strTimeCode;
    private int _nIndValue;
    private int _nIndTarget;

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
     * Returns the TimeCode
     * @return The TimeCode
     */
    public String getTimeCode(  )
    {
        return _strTimeCode;
    }

    /**
     * Sets the TimeCode
     * @param strTimeCode The TimeCode
     */
    public void setTimeCode( String strTimeCode )
    {
        _strTimeCode = strTimeCode;
    }

    /**
     * Returns the IndValue
     * @return The IndValue
     */
    public int getIndValue(  )
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
}
