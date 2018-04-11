Ext.define('App.view.version.VersionWindow', {
	extend : 'App.config.BaseWindow',
	requires : [ 'App.controller.version.VersionViewController'],
	controller : 'versionViewController',
	xtype : 'versionWindow',
	id : 'versionWindow',
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
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '版本号',
			name : 'versionNumber'
		},{
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '系统',
			name : 'systemId'
		},{
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '预计开始时间',
			name : 'predictStartTime'
		},{
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '预计发布时间',
			name : 'predictPublishTime'
		},{
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '开发时间',
			name : 'developmentTime'
		},{
			allowBlank : false,
			beforeLabelTextTpl : [ '<font color=red data-qtip=必填选项>*</font>' ],
			fieldLabel : '上线时间',
			name : 'uptime'
		} ]
	} ]
});