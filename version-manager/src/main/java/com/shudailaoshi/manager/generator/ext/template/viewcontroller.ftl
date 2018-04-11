Ext.define('App.controller.${packageName}.${entityName}ViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.${packageName}ViewController',
	control : {
		'${packageName}View ${packageName}List' : {
			beforerender : function(grid) {
				BaseUtil.createPermitTbar(grid, {
					'${moduleName}/${packageName}/save${entityName}' : function(permit) {
						BaseUtil.createView('${packageName}.${entityName}Window',permit,{storeId:'${packageName}.${entityName}Store'}).show();
					},
					'${moduleName}/${packageName}/update${entityName}' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							var win = BaseUtil.createView('${packageName}.${entityName}Window',permit,{storeId:'${packageName}.${entityName}Store'});
							win.down('form').loadRecord(record);
							win.show();
						});
					},
					'${moduleName}/${packageName}/remove${entityName}' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							BaseUtil.statusConfirm('确认删除此记录吗?',permit.url,record.get('id'),store);
						});
					},
					'${moduleName}/${packageName}/freeze' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							BaseUtil.statusConfirm('确认冻结此记录吗?',permit.url,record.get('id'),store);
						});
					},
					'${moduleName}/${packageName}/unfreeze' : function(permit) {
						BaseUtil.isSelection(grid,function(record){
							BaseUtil.statusConfirm('确认解冻此记录吗?',permit.url,record.get('id'),store);
						});
					}
				});
			},
			render : function() {
				BaseUtil.loadStore(Ext.StoreMgr.get('${packageName}.${entityName}Store'),Ext.getCmp('${packageName}View').down('${packageName}Search form').getForm().getFieldValues());
			}
		},
		'${packageName}View ${packageName}Search [action=search] ' : {
			click : function() {
				BaseUtil.loadStore(Ext.StoreMgr.get('${packageName}.${entityName}Store'),Ext.getCmp('${packageName}View').down('${packageName}Search form').getForm().getFieldValues());
			}
		},
		'${packageName}Window [action=save]': {
			click : function(btn) {
				var win = btn.up('window'),params = win.params;
				BaseUtil.submit(win.down('form'), params.url,function(data) {
					BaseUtil.toast(data.msg);
					Ext.StoreMgr.get(params.storeId).reload();
					win.close();
				});
			}
		}
	}
});