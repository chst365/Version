Ext.define('App.view.need.NeedList', {
	extend : 'App.config.BaseGrid',
	xtype : 'needList',
	autoSizeXtype : 'needList',
	store : Ext.create('App.store.need.NeedStore'),
	bbar : {
		xtype : 'pagingtoolbar',
		store : this.store,
		scrollable : true,
		displayInfo : true
	},
	columns : [ {
		xtype : 'rownumberer'
	},{
			text : '需求名称',
			dataIndex : 'needName'
		},{
			text : '优先级',
			dataIndex : 'take'
		},{
			text : '开发人',
			dataIndex : 'developName'
		},{
			text : '所属系统',
			dataIndex : 'systemId'
		},{
			text : '版本号',
			dataIndex : 'versionId'
		},{
			text : '测试状态',
			dataIndex : 'testStatus'
		},{
			text : '测试人',
			dataIndex : 'testName'
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