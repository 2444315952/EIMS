<template>
	<div id="Blitem">
		<el-row>
			<el-breadcrumb separator-class="el-icon-arrow-right">
				<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
				<el-breadcrumb-item>盘点单列表</el-breadcrumb-item>
			</el-breadcrumb>
		</el-row>
		<el-container>
			<el-main>
				
				<el-row>
					<el-col :span="4">
						<span class="dialog-footer" style="float: left;">
							<span class="font-style">审核状态：</span>
							<el-select v-model="valuetype" @change="changetype" style="width: 110px;" size="medium">
								<el-option v-for="item in options" :key="item.value" :label="item.label"
									:value="item.value">
								</el-option>
							</el-select>
						</span>
					</el-col>
					<el-col :span="7">
						<span class="dialog-footer">
							<span class="font-style">盘点日期:</span>&nbsp;
							<el-date-picker
							      v-model="value1"
							      type="date"
								  size="medium"
							      placeholder="选择日期">
							</el-date-picker>
						</span>
					</el-col>
					<el-col :span="7">
						<el-input style="width: 220px;" class="inline-input" placeholder="请输入仓库名称或备注" v-model="project" size="medium">
							<template #append>
								<el-button icon="el-icon-search" size="mediums" @click="test"></el-button>
							</template>
						</el-input>
					</el-col>
					<el-col :span="4">
						<el-button size="medium" type="danger" style="float: right;" v-show="delbut" @click="Pldelete(changeFun)">批量删除
						</el-button>
					</el-col>
					<el-col :span="2">
						<el-button @click="clickBut" style="float: right;" type="primary" size="medium">新增</el-button>
					</el-col>
				</el-row>
				<el-table ref="multipleTable" :data="tableData" :height="tableHeight" tooltip-effect="dark"
					style="width: 100%; height: 490px;" @selection-change="handleSelectionChange">
					<el-table-column type="selection" width="55">
					</el-table-column>
					<el-table-column prop="number" label="单据编号" width="120">
					</el-table-column>
					<el-table-column label="单据日期" width="130">
						<template #default="scope">{{ scope.row.date }}</template>
					</el-table-column>
					<el-table-column prop="address" label="盘点仓库"  width="120" show-overflow-tooltip>
					</el-table-column>
					<el-table-column prop="audited " label="审核状态"  width="120" show-overflow-tooltip>
					</el-table-column>
					<el-table-column prop="workPointName" label="工作点" width="120" show-overflow-tooltip>
					</el-table-column>
					<el-table-column prop="name" label="业务员" width="120">
					</el-table-column>
					<el-table-column prop="documentsNote " label="备注"  width="130" show-overflow-tooltip>
					</el-table-column>
					<el-table-column prop="operate" label="操作" show-overflow-tooltip>
						<template #default="scope">
							<el-tooltip class="item" effect="dark" content="查看" placement="top">
								<el-button size="mini" circle type="success" icon="el-icon-view" content="查看"
									@click="handleEdit(scope.$index, scope.row)"></el-button>
							</el-tooltip>
							<el-tooltip class="item" effect="dark" content="审核" placement="top">
								<el-button size="mini" type="info" circle icon="el-icon-s-check" @click="check(scope)">
								</el-button>
							</el-tooltip>
							<el-tooltip class="item" effect="dark" content="编辑" placement="top">
								<el-button size="mini" circle type="primary" icon="el-icon-edit-outline"
									@click="handleEdit(scope.$index, scope.row)"></el-button>
							</el-tooltip>
						</template>
					</el-table-column>
				</el-table>
				<el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange"
					:current-page="currentPage" :page-sizes="[2, 50, 100, 200]" :page-size="pageSize"
					layout="total, sizes, prev, pager, next, jumper" :total="total">
				</el-pagination>
			</el-main>
		</el-container>
	</div>
</template>


<script>
	export default {
		data() {
			return {
				total: 3,
				currentPage: 1,
				pageSize: 2,
				tableHeight: window.innerHeight,
				value1: '',
				defaultTime1: [
					new Date(2000, 1, 1, 12, 0, 0),
				],
				options: [{
						value: 'all',
						label: '全部仓库'
					},
					{
						value: 'model',
						label: '默认仓库'
					},
					{
						value: 'machine',
						label: '深圳分店'
					}
				],
				valuetype: 'all',
				options1: [{
						value: 'all',
						label: '全部类型'
					},
					{
						value: 'model',
						label: '以收取货单出库'
					},
					{
						value: 'machine',
						label: '退货购单出库'
					},
					{
						value: 'model',
						label: '调拨出库'
					},
					{
						value: 'model',
						label: '盘亏出库'
					},
				],
				valuetype: 'all',
				valuetype1: 'all',
				tableData: [{
					number: '131324',
					foldWarehouse: '默认仓',
					exportWarehouser: '深圳分店',
					date: '2016-05-03',
					name: '王小虎',
					address: '已作废'
				}, {
					number: '131324',
					foldWarehouse: '默认仓',
					exportWarehouser: '深圳分店',
					date: '2016-05-02',
					name: '王小虎',
					address: '已入库'
				}, {
					number: '131324',
					foldWarehouse: '默认仓',
					exportWarehouser: '深圳分店',
					date: '2016-05-04',
					name: '王小虎',
					address: '在途'
				}],
				multipleSelection: [],
				dialogTableVisible: false,
				dialogFormVisible: false,
				form: {
					name: '',
					region: '',
					date1: '',
					date2: '',
					delivery: false,
					type: [],
					resource: '',
					desc: ''
				},
				formLabelWidth: '120px'
			}
		},
		methods: {
			handleEdit(index, row) {
				console.log(index, row);
			},
			handleDelete(index, row) {
				console.log(index, row);
			},
			toggleSelection(rows) {
				if (rows) {
					rows.forEach(row => {
						this.$refs.multipleTable.toggleRowSelection(row);
					});
				} else {
					this.$refs.multipleTable.clearSelection();
				}
			},
			handleSelectionChange(val) {
				this.multipleSelection = val;
			}
		}
	}
</script>
<style>
	#Blitem .el-container {
			height: 100%;
			width: 100%;
		}
	
	#Blitem .el-header,
	#Blitem .el-footer {
			background-color: #B3C0D1;
			color: #333;
			text-align: center;
			line-height: 60px;
		}
	
	#Blitem .el-aside {
			background-color: #D3DCE6;
			color: #333;
			text-align: center;
		}
	
	#Blitem .el-main {
			background-color: #E9EEF3;
			color: #333;
			text-align: center;
			height: 600px;
	
		}
	
	
	#Blitem .el-row {
			margin-bottom: 5px;
		}
	
	#Blitem .el-col {
			border-radius: 4px;
		}
	
	#Blitem .row-bg {
			padding: 10px 0;
			background-color: #f9fafc;
		}
	
	#Blitem .item {
			margin-top: 10px;
			margin-right: 10px;
		}
	
	#Blitem .font-style {
			text-align: right;
			vertical-align: middle;
			font-size: 14px;
			color: #606266;
			line-height: 40px;
			-webkit-box-sizing: border-box;
			box-sizing: border-box;
		}
		
		#Blitem .el-dialog__body {
			padding: 5px;
		}
		
		#Blitem .el-table td,
		#Blitem .el-table th {
			padding: 6px 0;
		}
	
	
	
</style>
