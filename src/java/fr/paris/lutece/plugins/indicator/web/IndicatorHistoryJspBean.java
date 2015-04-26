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

import fr.paris.lutece.plugins.indicator.business.IndicatorHistory;
import fr.paris.lutece.plugins.indicator.business.IndicatorHistoryHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.util.url.UrlItem;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * This class provides the user interface to manage IndicatorHistory features ( manage, create, modify, remove )
 */
@Controller( controllerJsp = "ManageHistory.jsp", controllerPath = "jsp/admin/plugins/indicator/", right = "INDICATOR_MANAGEMENT" )
public class IndicatorHistoryJspBean extends ManageIndicatorJspBean
{
    ////////////////////////////////////////////////////////////////////////////
    // Constants

    // templates
    private static final String TEMPLATE_MANAGE_INDICATORHISTORYS = "/admin/plugins/indicator/manage_history.html";
    private static final String TEMPLATE_CREATE_INDICATORHISTORY = "/admin/plugins/indicator/create_history.html";
    private static final String TEMPLATE_MODIFY_INDICATORHISTORY = "/admin/plugins/indicator/modify_history.html";

    // Parameters
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_KEY = "key";

    // Properties for page titles
    private static final String PROPERTY_PAGE_TITLE_MANAGE_INDICATORHISTORYS = "indicator.manage_history.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_MODIFY_INDICATORHISTORY = "indicator.modify_history.pageTitle";
    private static final String PROPERTY_PAGE_TITLE_CREATE_INDICATORHISTORY = "indicator.create_history.pageTitle";

    // Markers
    private static final String MARK_INDICATORHISTORY_LIST = "history_list";
    private static final String MARK_INDICATORHISTORY = "history";
    private static final String MARK_KEY = "key";
    private static final String JSP_MANAGE_HISTORY = "jsp/admin/plugins/indicator/ManageHistory.jsp";

    // Properties
    private static final String MESSAGE_CONFIRM_REMOVE_INDICATORHISTORY = "indicator.message.confirmRemoveIndicatorHistory";
    private static final String PROPERTY_DEFAULT_LIST_INDICATORHISTORY_PER_PAGE = "indicator.listIndicatorHistorys.itemsPerPage";
    private static final String VALIDATION_ATTRIBUTES_PREFIX = "indicator.model.entity.history.attribute.";

    // Views
    private static final String VIEW_MANAGE_INDICATORHISTORYS = "manageIndicatorHistorys";
    private static final String VIEW_CREATE_INDICATORHISTORY = "createIndicatorHistory";
    private static final String VIEW_MODIFY_INDICATORHISTORY = "modifyIndicatorHistory";

    // Actions
    private static final String ACTION_CREATE_INDICATORHISTORY = "createIndicatorHistory";
    private static final String ACTION_MODIFY_INDICATORHISTORY = "modifyIndicatorHistory";
    private static final String ACTION_REMOVE_INDICATORHISTORY = "removeIndicatorHistory";
    private static final String ACTION_CONFIRM_REMOVE_INDICATORHISTORY = "confirmRemoveIndicatorHistory";

    // Infos
    private static final String INFO_INDICATORHISTORY_CREATED = "indicator.info.history.created";
    private static final String INFO_INDICATORHISTORY_UPDATED = "indicator.info.history.updated";
    private static final String INFO_INDICATORHISTORY_REMOVED = "indicator.info.history.removed";

    // Session variable to store working values
    private IndicatorHistory _history;

    @View( value = VIEW_MANAGE_INDICATORHISTORYS, defaultView = true )
    public String getManageIndicatorHistorys( HttpServletRequest request )
    {
        _history = null;

        String strKey = request.getParameter( PARAMETER_KEY );
        List<IndicatorHistory> listIndicatorHistorys = (List<IndicatorHistory>) IndicatorHistoryHome.getIndicatorHistorysList( strKey );
        Map<String, Object> model = getPaginatedListModel( request, MARK_INDICATORHISTORY_LIST, listIndicatorHistorys,
                JSP_MANAGE_HISTORY );
        model.put( MARK_KEY, strKey );
        return getPage( PROPERTY_PAGE_TITLE_MANAGE_INDICATORHISTORYS, TEMPLATE_MANAGE_INDICATORHISTORYS, model );
    }

    /**
     * Returns the form to create a history
     *
     * @param request The Http request
     * @return the html code of the history form
     */
    @View( VIEW_CREATE_INDICATORHISTORY )
    public String getCreateIndicatorHistory( HttpServletRequest request )
    {
        String strKey = request.getParameter( PARAMETER_KEY );
        _history = ( _history != null ) ? _history : new IndicatorHistory(  );
        _history.setIndKey(strKey);
        Map<String, Object> model = getModel(  );
        model.put( MARK_INDICATORHISTORY, _history );

        return getPage( PROPERTY_PAGE_TITLE_CREATE_INDICATORHISTORY, TEMPLATE_CREATE_INDICATORHISTORY, model );
    }

    /**
     * Process the data capture form of a new history
     *
     * @param request The Http Request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_CREATE_INDICATORHISTORY )
    public String doCreateIndicatorHistory( HttpServletRequest request )
    {
        populate( _history, request );

        // Check constraints
        if ( !validateBean( _history, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirectView( request, VIEW_CREATE_INDICATORHISTORY );
        }

        IndicatorHistoryHome.create( _history );
        addInfo( INFO_INDICATORHISTORY_CREATED, getLocale(  ) );

        Map<String,String> mapParameters = new HashMap<String,String>();
        mapParameters.put( PARAMETER_KEY , _history.getIndKey() );
        return redirect( request, VIEW_MANAGE_INDICATORHISTORYS , mapParameters );
    }

    /**
     * Manages the removal form of a history whose identifier is in the http
     * request
     *
     * @param request The Http request
     * @return the html code to confirm
     */
    @Action( ACTION_CONFIRM_REMOVE_INDICATORHISTORY )
    public String getConfirmRemoveIndicatorHistory( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID ) );
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_INDICATORHISTORY ) );
        url.addParameter( PARAMETER_ID, nId );

        String strMessageUrl = AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_INDICATORHISTORY,
                url.getUrl(  ), AdminMessage.TYPE_CONFIRMATION );

        return redirect( request, strMessageUrl );
    }

    /**
     * Handles the removal form of a history
     *
     * @param request The Http request
     * @return the jsp URL to display the form to manage history
     */
    @Action( ACTION_REMOVE_INDICATORHISTORY )
    public String doRemoveIndicatorHistory( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID ) );
        IndicatorHistoryHome.remove( nId );
        addInfo( INFO_INDICATORHISTORY_REMOVED, getLocale(  ) );

        Map<String,String> mapParameters = new HashMap<String,String>();
        mapParameters.put( PARAMETER_KEY , _history.getIndKey() );
        return redirect( request, VIEW_MANAGE_INDICATORHISTORYS , mapParameters );
    }

    /**
     * Returns the form to update info about a history
     *
     * @param request The Http request
     * @return The HTML form to update info
     */
    @View( VIEW_MODIFY_INDICATORHISTORY )
    public String getModifyIndicatorHistory( HttpServletRequest request )
    {
        int nId = Integer.parseInt( request.getParameter( PARAMETER_ID ) );

        if ( ( _history == null ) || ( _history.getId(  ) != nId ) )
        {
            _history = IndicatorHistoryHome.findByPrimaryKey( nId );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_INDICATORHISTORY, _history );

        return getPage( PROPERTY_PAGE_TITLE_MODIFY_INDICATORHISTORY, TEMPLATE_MODIFY_INDICATORHISTORY, model );
    }

    /**
     * Process the change form of a history
     *
     * @param request The Http request
     * @return The Jsp URL of the process result
     */
    @Action( ACTION_MODIFY_INDICATORHISTORY )
    public String doModifyIndicatorHistory( HttpServletRequest request )
    {
        populate( _history, request );

        // Check constraints
        if ( !validateBean( _history, VALIDATION_ATTRIBUTES_PREFIX ) )
        {
            return redirect( request, VIEW_MODIFY_INDICATORHISTORY, PARAMETER_ID, _history.getId(  ) );
        }

        IndicatorHistoryHome.update( _history );
        addInfo( INFO_INDICATORHISTORY_UPDATED, getLocale(  ) );

        Map<String,String> mapParameters = new HashMap<String,String>();
        mapParameters.put( PARAMETER_KEY , _history.getIndKey() );
        return redirect( request, VIEW_MANAGE_INDICATORHISTORYS , mapParameters );
    }
}
