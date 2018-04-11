Ext.define('App.view.need.NeedSearch', {
	extend : 'App.config.BaseFieldSet',
	xtype : 'needSearch',
	items : [ {
		xtype : 'baseSearchForm',
		items : [ {
			items : [ {
				fieldLabel : '需求名称',
				name : 'needName'
			},{
				fieldLabel : '优先级',
				name : 'take'
			},{
				fieldLabel : '开发人',
				name : 'developName'
			},{
				fieldLabel : '所属系统',
				name : 'systemId'
			},{
				fieldLabel : '版本号',
				name : 'versionId'
			},{
				xtype:'numberfield',
				fieldLabel : '测试状态',
				name : 'testStatus'
			},{
				fieldLabel : '测试人',
				name : 'testName'
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
