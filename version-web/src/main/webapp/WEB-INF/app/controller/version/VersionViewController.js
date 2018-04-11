Ext.define('App.controller.version.VersionViewController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.versionViewController',
	menu : Ext.widget('menu'),
	control : {
		'versionView versionList' : {
			beforerender : function(grid) {
				var buttons = {
					'ver/version/saveVersion' : function(permit) {
						BaseUtil.createView('version.VersionWindow',permit,{storeId:'version.VersionStore'}).show();
					}
				};
				BaseUtil.createPermitTbar(grid, buttons);
			},
//			itemcontextmenu : function(view, record, item, index, e) {
//				var id = record.get('id');
//				var menuitems = {
//					'ver/version/updateVersion' : function(permit) {
//						var win = BaseUtil.createView('version.VersionWindow',permit,{storeId:'version.VersionStore'});
//						win.down('form').loadRecord(record);
//						win.show();
//					},
//					'ver/version/removeVersion' : function(permit) {
//						BaseUtil.statusConfirm('确认删除此记录吗?',permit.url,id,store);
//					}
//				};
//				var status = record.get('status'),store = Ext.StoreMgr.get('version.VersionStore');
//				if (status === StatusEnum.NORMAL.getValue()) {
//					menuitems['ver/version/freeze'] = function(permit) {
//						BaseUtil.statusConfirm('确认冻结此记录吗?',permit.url,id,store);
//					}
//				}else if (status === StatusEnum.FREEZE.getValue()) {
//					menuitems['ver/version/unfreeze'] = function(permit) {
//						BaseUtil.statusConfirm('确认解冻此记录吗?',permit.url,id,store);
//					}
//				}
//				BaseUtil.createPermitMenu(view, this.menu, e, menuitems);
//			},
			render:function ( self, eOpts ) {
				Ext.StoreMgr.get('version.VersionStore').load();
			}
		},
		'versionView versionDevList' : {
			render:function ( self, eOpts ) {
				Ext.StoreMgr.get('version.VersionDevStore').load();
			}
		},
		'versionWindow [action=save]': {
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