Ext.define('App.view.version.VersionView', {
	extend : 'App.config.BasePanel',
	requires : [ 'App.controller.version.VersionViewController',
			 'App.view.version.VersionList',
			 'App.view.version.VersionDevList' ],
	controller : 'versionViewController',
	xtype : 'versionView',
	id : 'versionView',
	items : [  {
		xtype : 'versionList'
	},{
		xtype : 'versionDevList'
	} ]
});