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
package fr.paris.lutece.plugins.indicator.web;

import fr.paris.lutece.plugins.indicator.business.Indicator;
import fr.paris.lutece.plugins.indicator.business.IndicatorHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.url.UrlItem;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * This class provides the user interface to manage Indicator features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageIndicators.jsp", controllerPath = "jsp/admin/plugins/indicator/", right = "INDICATOR_MANAGEMENT" )
public class IndicatorJspBean extends ManageIndicatorJspBean
{
    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_INDICATORS = "/admin/plugins/indicator/manage_indicators.html";
    private static final String TEMPLATE_CREATE_INDICATOR = "/admin/plugins/indicator/create_indicator.html";
    private static final String TEMPLATE_MODIFY_INDICATOR = "/admin/plugins/indicator/modify_indicator.html";

    // Parameters
    private static final String PARAMETER_ID_INDICATOR = "id";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_INDICATORS = "indicator.manage_indicators.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_INDICATOR = "indicator.modify_indicator.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_INDICATOR = "indicator.create_indicator.pageTitle";

    // Markers
    private static final String MARK_INDICATOR_LIST = "indicator_list";
    private static final String MARK_INDICATOR = "indicator";
    private static final String JSP_MANAGE_INDICATORS = "jsp/admin/plugins/indicator/ManageIndicators.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_INDICATOR = "indicator.message.confirmRemoveIndicator";
    private static final String PROPERTY_DEFAULT_LIST_INDICATOR_PER_PAGE = "indicator.listIndicators.itemsPerPage";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "indicator.model.entity.indicator.attribute.";

    // Views
    private static final String VIEW_MANAGE_INDICATORS = "manageIndicators";
    private static final String VIEW_CREATE_INDICATOR = "createIndicator";
    private static final String VIEW_MODIFY_INDICATOR = "modifyIndicator";

    // Actions
    private static final String ACTION_CREATE_INDICATOR = "createIndicator";
    private static final String ACTION_MODIFY_INDICATOR = "modifyIndicator";
    private static final String ACTION_REMOVE_INDICATOR = "removeIndicator";
    private static final String ACTION_CONFIRM_REMOVE_INDICATOR = "confirmRemoveIndicator";

    // Infos
    private static final String INFO_INDICATOR_CREATED = "indicator.info.indicator.created";
    private static final String INFO_INDICATOR_UPDATED = "indicator.info.indicator.updated";
    private static final String INFO_INDICATOR_REMOVED = "indicator.info.indicator.removed";

    // Session variable to store working values
    private Indicator _indicator;

    @View( value = VIEW_MANAGE_INDICATORS, defaultView = true )
    public String getManageIndicators( HttpServletRequest request )
    {
        _indicator = null;

        List<Indicator> listIndicators = (List<Indicator>) IndicatorHome.getIndicatorsList(  );
        Map<String, Object> model = getPaginatedListModel( request, MARK_INDICATOR_LIST, listIndicators,
                JSP_MANAGE_INDICATORS );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE_INDICATORS, TEMPLATE_MANAGE_INDICATORS, model );
    }

    /**
     * Returns the form to create a indicator
     *
     * @param request The Http request
     * @return the html code of the indicator form
     */
    @View( VIEW_CREATE_INDICATOR )
    public String getCreateIndicator( HttpServletRequest request )
    {
        _indicator = ( _indicator != null ) ? _indicator : new Indicator(  );

        Map<String, Object> model = getModel(  );
        model.put( MARK_INDICATOR, _indicator );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_INDICATOR, TEMPLATE_CREATE_INDICATOR, model );
    }

    /**
     * Process the data capture form of a new indicator
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_INDICATOR )
    public String doCreateIndicator( HttpServletRequest request )
    {
        populate( _indicator, request );

        // Check constraints
        if ( !validateBean( _indicator, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_INDICATOR );
        }

        IndicatorHome.create( _indicator );
        addInfo( INFO_INDICATOR_CREATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_INDICATORS );
    }

    /**
     * Manages the removal form of a indicator whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_INDICATOR )
    public String getConfirmRemoveIndicator( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_INDICATOR ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_INDICATOR ) );
        url.addParameter( PARAMETER_ID_INDICATOR, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_INDICATOR,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a indicator
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage indicators
     */
    @Action( ACTION_REMOVE_INDICATOR )
    public String doRemoveIndicator( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_INDICATOR ) );
        IndicatorHome.remove( nId );
        addInfo( INFO_INDICATOR_REMOVED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_INDICATORS );
    }

    /**
     * Returns the form to update info about a indicator
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_INDICATOR )
    public String getModifyIndicator( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID_INDICATOR ) );

        if ( ( _indicator == null ) || ( _indicator.getId(  ) != nId ) )
        {
            _indicator = IndicatorHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_INDICATOR, _indicator );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_INDICATOR, TEMPLATE_MODIFY_INDICATOR, model );
    }

    /**
     * Process the change form of a indicator
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_INDICATOR )
    public String doModifyIndicator( HttpServletRequest request )
    {
        populate( _indicator, request );

        // Check constraints
        if ( !validateBean( _indicator, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_INDICATOR, PARAMETER_ID_INDICATOR, _indicator.getId(  ) );
        }

        IndicatorHome.update( _indicator );
        addInfo( INFO_INDICATOR_UPDATED, getLocale(  ) );

        return redirectView( request, VIEW_MANAGE_INDICATORS );
    }
}
