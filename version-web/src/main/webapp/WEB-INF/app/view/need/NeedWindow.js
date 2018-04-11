Ext.define('App.view.need.NeedWindow', {
	extend : 'App.config.BaseWindow',
	requires : [ 'App.controller.need.NeedViewController'],
	controller : 'needViewController',
	xtype : 'needWindow',
	id : 'needWindow',
	buttons : [ {
		text : '保存',
		action : 'save'
	}, {
		text : '关闭',
		handler : function() {
			this.up('window').close();
		}
	} ],
	items : [ {
		xtype : 'baseWindowForm',
		items : [{
			xtype:'hidden',
			name:'id'
		},{
			columnWidth : .30,
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '需求名称',
			name : 'needName'
		},{
			columnWidth : .25,
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '开发人',
			name : 'developName'
		},{
			columnWidth : .25,
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '所属系统',
			name : 'systemId'
		},{
			columnWidth : .25,
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '版本号',
			name : 'versionId'
		},{
			columnWidth : .25,
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '测试状态',
			name : 'testStatus'
		},{
			columnWidth : .25,
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '测试人',
			name : 'testName'
		} ]
	} ]
});