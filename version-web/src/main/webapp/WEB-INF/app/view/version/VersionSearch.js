Ext.define('App.view.version.VersionSearch', {
	extend : 'App.config.BaseFieldSet',
	xtype : 'versionSearch',
	items : [ {
		xtype : 'baseSearchForm',
		items : [ {
			items : [ {
				fieldLabel : '版本号',
				name : 'versionNumber'
			},{
				fieldLabel : '系统',
				name : 'systemId'
			},{
				fieldLabel : '预计开始时间',
				name : 'predictStartTime'
			},{
				fieldLabel : '预计发布时间',
				name : 'predictPublishTime'
			},{
				fieldLabel : '开发时间',
				name : 'developmentTime'
			},{
				fieldLabel : '上线时间',
				name : 'uptime'
			} ]
		}, {
			items : [ {
				xtype : 'baseStartDate',
				columnWidth : .25,
			}, {
				xtype : 'baseEndDate',
				columnWidth : .2,
				labelWidth : 10
			}, {
				xtype : 'button',
				text : '查询',
				action : 'search',
				columnWidth : .15,
				style : 'margin-left:15px'
			}, {
				xtype : 'button',
				text : '重置',
				columnWidth : .15,
				style : 'margin-left:15px',
				handler : function(me) {
					me.up('form').getForm().reset();
				}
			} ]
		} ]
	} ]
});
