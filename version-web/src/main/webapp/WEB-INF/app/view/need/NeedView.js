Ext.define('App.view.need.NeedView', {
	extend : 'App.config.BasePanel',
	requires : [ 'App.controller.need.NeedViewController',
			'App.view.need.NeedSearch', 'App.view.need.NeedList' ],
	controller : 'needViewController',
	xtype : 'needView',
	id : 'needView',
	items : [ {
		xtype : 'needSearch'
	}, {
		xtype : 'needList'
	} ]
});