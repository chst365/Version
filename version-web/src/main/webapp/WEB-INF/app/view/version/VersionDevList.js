Ext.define('App.view.version.VersionDevList', {
	extend : 'App.config.BaseGrid',
	xtype : 'versionDevList',
	autoSizeXtype : 'versionDevList',
	store : Ext.create('App.store.version.VersionStore',{
		storeId:'version.VersionDevStore'
	}),
	bbar : {
		xtype : 'pagingtoolbar',
		store : this.store,
		scrollable : true,
		displayInfo : true
	},
	title:'最近开发版本',
	columns : [ {
		xtype : 'rownumberer'
	},{
			text : '版本号',
			dataIndex : 'versionNumber'
		},{
			text : '系统',
			dataIndex : 'systemId'
		},{
			text : '预计开始时间',
			dataIndex : 'predictStartTime'
		},{
			text : '预计发布时间',
			dataIndex : 'predictPublishTime'
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