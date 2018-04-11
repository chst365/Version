Ext.define('App.view.version.VersionList', {
	extend : 'App.config.BaseGrid',
	xtype : 'versionList',
	autoSizeXtype : 'versionList',
	store : Ext.create('App.store.version.VersionStore'),
	bbar : {
		xtype : 'pagingtoolbar',
		store : this.store,
		scrollable : true,
		displayInfo : true
	},
	title:'当前生产版本',
	columns : [ {
		xtype : 'rownumberer'
	},{
			text : '版本号',
			dataIndex : 'versionNumber'
		},{
			text : '系统',
			dataIndex : 'systemId'
		},{
			text : '开发时间',
			dataIndex : 'developmentTime'
		},{
			text : '上线时间',
			dataIndex : 'uptime'
		},{
			text : '创建时间',
			dataIndex : 'createTime',
			renderer : function(value) {
				return DateUtil.timeToString(value,DateUtil.TIME);
			}
		},{
			text : '状态',
			dataIndex : 'status',
			renderer : function(value) {
				return StatusEnum.getHtml(value);
			}
		}]
});