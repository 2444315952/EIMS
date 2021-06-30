<template>
	<div id="Addblitem">
		<el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/blitem">盘点单列表</a></el-breadcrumb-item>
			<el-breadcrumb-item>盘点单</el-breadcrumb-item>
		</el-breadcrumb>
		<el-container style="padding-top:15px;">
			<el-main style="background-color:#F9FAFC">
				<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
					<el-row></el-row>
					<el-row></el-row>
					<el-row>
						<el-col :span="8">
							<el-form-item label="单据编号:" prop="blitemDocunum">
								<el-input v-model="ruleForm.blitemDocunum" :disabled="true"
									style="float: left; width: 250px;"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="8">
							<el-form-item label="单据日期:" prop="documentDate">
								<el-date-picker type="datetime" placeholder="选择日期" v-model="ruleForm.documentDate"
									:disabled="isdisabled" style="width:250px; float: left;"></el-date-picker>
							</el-form-item>
						</el-col>
						<el-col :span="7">
							<el-form-item label="盘点仓库" style="float:left;" prop="warehouseName">
								<el-input v-model="ruleForm.warehouseName" style="width:250px;" size="medium" disabled>
									<template #append>
										<el-button icon="el-icon-plus" size="small"
											@click="warehouse.dialogVisible = true;warehouseLoadData()"></el-button>
									</template>
								</el-input>
							</el-form-item>
						</el-col>
						<el-dialog title="仓库" v-model="warehouse.dialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="warehouse.searchInput" placeholder="请搜索仓库名称" size="small">
										<template #append>
											<el-button icon="el-icon-search" @click="warehouseLoadData()" size="mini">
											</el-button>
										</template>
									</el-input>
								</el-col>
						
							</el-row>
						
							<el-table :data="warehouse.tableData" max-height="286" style="height: 286px;"
								highlight-current-row @current-change="warehouseSelectionChange">
								<el-table-column property="warehouseName" label="仓库名称"></el-table-column>
								<el-table-column property="employeeName" label="联系人"></el-table-column>
								<el-table-column property="phone" label="联系电话"></el-table-column>
								<el-table-column property="warehouseAddress" label="联系地址"></el-table-column>
							</el-table>
							<el-row>
								<el-col :span="24">
									<el-pagination style="float: right;margin-top: 15px;"
										@size-change="warehouseSizeChange" @current-change="warehouseCurrentChange"
										:page-sizes="[10,20,40,80]" :page-size="warehouse.pageParam.pageSize"
										layout="total, sizes, prev, pager, next, jumper" :total="warehouse.tableTotal">
									</el-pagination>
								</el-col>
							</el-row>
						
							<template #footer>
								<span class="dialog-footer">
									<el-button @click="warehouse.dialogVisible = false" size="medium">取 消</el-button>
									<el-button type="primary" @click="warehouseConfirmButton" size="medium">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>
					</el-row>
					<br/>
					<el-row>
						<!-- <el-col :span="1"></el-col>
						<el-col :span="24"> -->
							<el-table ref="multipleTable" :data="ruleForm.baldetailList" :height="tableHeight"
								max-height="265" tooltip-effect="dark" 
								style="width:100%; text-align :center; height: 265px;"
								@selection-change="handleSelectionChange">
								<el-table-column label="操作" width="90">
									<template #default="scope">
										<el-button type="primary" icon="el-icon-plus" size="mini" @click="addRow()"
											circle :disabled="isdisabled">
										</el-button>
										<el-button type="primary" icon="el-icon-minus" size="mini"
											@click="removeRow(scope.$index)" circle :disabled="isdisabled"></el-button>
									</template>
								</el-table-column>
								<el-table-column label="产品名称" width="160">
									<template #default="append">
										<el-input v-model="ruleForm.baldetailList[append.$index].productName"
											placeholder="请选择产品" :disabled="isdisabled">
											<template #suffix>
												<el-button icon="el-icon-more" style="border: none;" size="small"
													@click="getProduct()" :disabled="isdisabled"></el-button>
					
											</template>
										</el-input>
									</template>
								</el-table-column>
								<el-table-column label="产品图片" width="140">
									<template #default="scope">
										<img v-if="typeof(this.ruleForm.baldetailList[0].productId) != 'undefined'"
											:src="ruleForm.baldetailList[scope.$index].productPicture"
											style="width: 40px; height: 40px;" />
									</template>
								</el-table-column>
								<el-table-column prop="specModel" label="规格" width="125">
								</el-table-column>
								<el-table-column prop="productUnit" label="单位" width="125">
								</el-table-column>
								<el-table-column label="库存数量" width="120" prop="inventory" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="总成本" width="120" prop="totalCost">
								</el-table-column>
								<el-table-column label="盘点数量" width="160" prop=" physicalInventory" show-overflow-tooltip>
									<template #default="scope">
										<el-input-number :disabled="isdisabled" v-if="typeof(this.ruleForm.baldetailList[0].productId) !='undefined'"
											v-model="ruleForm.baldetailList[scope.$index].physicalInventory" @change="changephysicalAmount(scope.$index)"
											size="small" :min="1" :precision="0">
										</el-input-number>
									</template>
								</el-table-column>
								<el-table-column label="盘点金额" prop="physicalAmount" width="120" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="数量差额" prop="quantityVariance" width="125" show-overflow-tooltip>
								</el-table-column>
								<el-table-column label="差异金额" prop="differenceAmount" width="125" show-overflow-tooltip>
								</el-table-column>
							</el-table>
						<!-- </el-col>
						<el-col :span="1"></el-col> -->
					</el-row>
					<br/>
					<br/>
					<el-row>
						<el-col :span="7">
							<el-form-item label="单据备注:" prop="documentsNote">
								<el-input type="textarea" v-model="ruleForm.documentsNote" :disabled="isdisabled"></el-input>
							</el-form-item>
						</el-col>
						<el-col :span="2"></el-col>
						<el-col :span="7">
							<el-form-item label="业务员:" prop="employeeName">
								<el-select v-model="ruleForm.employeeName" @change="selectEmployee"
									:disabled="isdisabled" placeholder="请选择" @click="queryEmployee()"
									style="float:left; width: 250px;">
									<el-option v-for="item in employeeList" :label="item.employeeName"
										:value="item.employeeName"></el-option>
								</el-select>
							</el-form-item>
						</el-col>
					</el-row>
					<br/>
					<el-row>
						<el-col :span="8"></el-col>
						<el-col :span="5">
							<el-form-item>
								<el-button type="primary" @click="submitForm('ruleForm')">保存</el-button>
								<el-button @click="$router.push({name:'Blitem'})">取消</el-button>
							</el-form-item>
						</el-col>
						<el-col :span="4"></el-col>
						<el-col :span="7">
							<el-form-item label="驳回原因:" prop="reason" v-if="ruleForm.audited==2">
								<span class="ReasonClass" >{{ruleForm.reason}}</span>
							</el-form-item>
						</el-col>
					</el-row>
				</el-form>
				<el-dialog title="选择产品" v-model="setProduct" :width="'800px'">
					<el-row>
						<el-col :span="17"></el-col>
						<el-col :span="7">
							<el-input class="inline-input" placeholder="请输入产品名" @keyup.enter.native="search"
								style="width:180px; padding-bottom: 6px;" v-model="searchInput" size="medium">
								<template #append>
									<el-button icon="el-icon-search" size="small" @click="search"></el-button>
								</template>
							</el-input>
						</el-col>
					</el-row>
					<el-row></el-row>
					<el-row></el-row>
					<el-row>
						<!-- <el-col :span="1"></el-col>
						<el-col :span="22"> -->
						<el-table :data="inventory" tooltip-effect="dark"
							style="width: 100%; text-align: center; height: 240px;" max-height="240"
							@selection-change="ProductSelectChange">
							<el-table-column type="selection" width="55">
							</el-table-column>
							<el-table-column label="仓库" width="125" property="warehouseName">
							</el-table-column>
							<el-table-column label="产品名称" width="130" property="productName">
							</el-table-column>
							<el-table-column label="市场价" width="120" property="marketPrice">
							</el-table-column>
							<el-table-column label="进价" property="purchasePrice" width="120" show-overflow-tooltip>
							</el-table-column>
							<el-table-column property="inventory" label="库存量" width="120" show-overflow-tooltip>
							</el-table-column>
							<el-table-column property="totalCost" label="总成本" width="120" show-overflow-tooltip>
							</el-table-column>
						</el-table>
						<!-- </el-col>
						<el-col :span="1"></el-col> -->
					</el-row>
					<el-row>
						<el-col :span="5"></el-col>
						<el-col :span="14">
							<el-pagination :current-page="queryForm.pageNum" :page-sizes="[2, 4, 6, 8]"
								:page-size="queryForm.pageSize" layout=" total, sizes, prev, pager, next, jumper"
								:total="queryForm.total" @size-change="ProductSizeChange"
								@current-change="ProductCurrentChange" />
						</el-col>
						<el-col :span="5"></el-col>
					</el-row>
					<template #footer>
						<span class="dialog-footer">
							<el-button @click="setProduct = false">取 消</el-button>
							<el-button type="primary" @click="AddProduct">确定</el-button>
						</span>
					</template>
				</el-dialog>
			</el-main>
		</el-container>
	</div>
</template>


<script>
	export default {
		data() {
			return {
				wareRow:null,
				sourceRowIndex: 0,
				multipleSelection:'',
				queryForm: {
					pageNum: 1,
					pageSize: 2,
					total: ''
					//workPointId: 1
					//currentPage,pagesize:对应后端的
				},
				queryType:'all',
				inventory:[{}],
				isAdd: true,
				SelecFoltList:[],
				employeeList:[],
				Product: [{}],
				searchInput:[],
				setProduct: false,
				isdisabled: false,
				docuNumSequence: '',
				sourceRowIndex: 0,
				warehouse: {
					dialogVisible: false,
					searchInput: '',
					tableData: [],
					tableTotal: '',
					singleSelection: {},
					pageParam: {
						"pageNum": 1,
						"pageSize": 10
					}
				},
				ruleForm: {
					reason:'',
					ProductNumber:1,
					baldetailList:[{}],
					blitemDocunum:'',
					warehouseName:'',
					employeeName:'',
					OutboundQuantity: '',
					documentsNote: '',
				},
				rules: {
					blitemDocunum: [{
							required: true,
							message: '请输入盘点单号',
							trigger: 'blur'
						},
						{
							min: 3,
							max: 20,
							message: '长度在 3 到 20 个字符',
							trigger: 'blur'
						}
					],
					warehouseName: [{
						required: true,
						message: '请选择要盘点的仓库',
						trigger: 'change'
					}],
					documentDate: [{
						type: 'date',
						required: true,
						message: '请选择日期',
						trigger: 'change'
					}],
					employeeName: [{
						required: true,
						message: '请选择业务员',
						trigger: 'change'
					}],
				}
			};
		},
		methods: {
			ProductSelectChange(val){
				console.log("baoqi"+this.multipleSelection)
				this.multipleSelection=val
			},
			
			changephysicalAmount(index){
				this.multipleSelection.forEach(m=>{
					
					const detail=this.ruleForm.baldetailList[index]
					// this.ruleForm.baldetailList[index].physicalInventory=1
					const total=m.purchasePrice*m.inventory //总成本=采购价*库存
					this.ruleForm.baldetailList[index].totalCost=total
					const totalAmount=m.purchasePrice * detail.physicalInventory//盘点金额=采购价*盘点数量
					this.ruleForm.baldetailList[index].physicalAmount=totalAmount
					const diffAmount=totalAmount-m.totalCost//差异金额=盘点金额-总成本
					this.ruleForm.baldetailList[index].differenceAmount=diffAmount
					const diffQuantity=detail.physicalInventory-m.inventory
					this.ruleForm.baldetailList[index].quantityVariance=diffQuantity
					console.log("采购价：")
					console.log(m.purchasePrice)
					console.log("总成本：")
					console.log(total)
					console.log("盘点金额：")
					console.log(totalAmount)
					console.log("差异金额：")
					console.log(diffAmount)
					console.log("数量差额")
					console.log(diffQuantity)
					// this.ruleForm.baldetailList[this.sourceRowIndex] =this.ruleForm.baldetailList[index]
					// this.sourceRowIndex++
				})
				//计算交易金额
				this.calcTransactionAmount()
			},
			
			warehouseLoadData() {
				this.axios({
					url: 'http://localhost:8089/eims/warehouse/search',
					method: 'get',
					params: Object.assign({
						'warehouseName': this.warehouse.searchInput
					}, this.warehouse.pageParam)
				}).then(response => {
					this.warehouse.tableData = response.data.list
					this.warehouse.tableTotal = response.data.total
				}).catch(error => {
			
				})
			},
			warehouseSelectionChange(val) {
				this.wareRow = val
				this.warehouse.singleSelection = val
			},
			warehouseSizeChange(val) {
				this.warehouse.pageParam.pageSize = val
				this.warehouseLoadData()
			},
			warehouseCurrentChange(val) {
				this.warehouse.pageParam.pageNum = val
				this.warehouseLoadData()
			},
			warehouseConfirmButton() {
				if (this.wareRow == null) {
					this.$message({
						type: 'info',
						message: '请选择出库仓库'
					});
				} else {
					this.ruleForm.warehouseId = this.warehouse.singleSelection.warehouseId
					this.ruleForm.warehouseName = this.warehouse.singleSelection.warehouseName
					this.warehouse.dialogVisible = false
				}
				this.ruleForm.baldetailList=[{}]
			},
			
			
			calcTransactionAmount(){
				var transactionAmount=0
				this.ruleForm.baldetailList.forEach(detail=>{
					transactionAmount+=detail.physicalAmount
				})
				this.ruleForm.transactionAmount=transactionAmount
				console.log("盘点金额：")
				console.log(this.ruleForm.transactionAmount)
			},
			
			// AddProduct(){
			// 	this.mult
			// },
			
			//选择库存中的商品进行入库
			AddProduct(){
				console.log("选中的商品：")
				console.log(this.multipleSelection)
				if(this.multipleSelection.length>0){
					this.setProduct=false
					this.ruleForm.baldetailList=this.multipleSelection
				}else{
					this.$message({
						type: 'info',
						message: '要盘点的商品'
					})
				}
			},
			//点击按钮查询盘点仓库的库存
			getProduct(index){
				this.sourceRowIndex=index
				if(this.ruleForm.warehouseName.length==0){
					this.$message({
						type: 'info',
						message: '请选择盘点仓库'
					})
					this.setProduct=false
				}else{
					this.setProduct=true
					this.axios({
						method:'get',
						url:'http://localhost:8089/eims/inventory/all',
						params:{
							"warehouseId":this.ruleForm.warehouseId
						}
					}).then(res=>{
						console.log(res)
						this.queryForm.total=res.data.total
						this.inventory=res.data.list
					})
				}
				
			},
			
			//生成单据编号，和时间
			getDocuDate(prefix) {
				const nowDate = new Date()
				var month = nowDate.getMonth() + 1
			
				month = month > 10 ? month : '0' + month
				var date = nowDate.getDate()
				date = date > 10 ? date : '0' + date
				this.ruleForm.blitemDocunum = prefix + "-" + nowDate.getFullYear() + month + date + "-"
				this.axios({
					url: 'http://localhost:8089/eims/blitem/search',
					method: 'get',
					params: {
						'blitemDocunum': this.ruleForm.blitemDocunum
					}
				}).then(res => {
					console.log(res)
					const docuNumSequence = (Array(5).join(0) + ( res.data.list.length + 1)).slice(-5)
					this.ruleForm.blitemDocunum = this.ruleForm.blitemDocunum + docuNumSequence
				}).catch(err => {
			
				})
			},
			
			//库存表格页大小
			ProductSizeChange(val) {
				//this.loding = true
				this.queryForm.pageSize = val
				if (this.queryType == 'all') {
					this.getProduct()
				} else if (this.queryType == 'search')
					this.search()
			},
			
			//库存表当前页
			ProductCurrentChange(val) {
				//this.loading = true
				this.queryForm.pageNum=val
				if (this.queryType == 'all') {
					this.getProduct()
				} else if (this.queryType == 'search')
					this.search()
			},
			
			//库存表的搜索框查询
			search() {
				this.queryType = 'search'
				this.queryForm.productName = this.searchInput
				this.axios({
					method: 'get',
					url: 'http://localhost:8089/eims/inventory/search',
					params: this.queryForm
				}).then(res => {
					console.log(res)
					this.queryForm.total = res.data.total
					this.inventory = res.data.list
				}).catch(err => {
			
				})
			},
			
			
			//下拉框取值得时候绑定仓库id
			selectFoldWarehouse(){
				this.SelecFoltList.forEach(w=>{
					if(w.warehouseName==this.ruleForm.warehouseName){
						this.ruleForm.warehouseId=w.warehouseId
						return false
					}
				})
				this.ruleForm.baldetailList=[{}]
			},
			
			//下拉框查询仓库
			queryFoldWarehouse(){
				this.axios({
					method:'get',
					url:'http://localhost:8089/eims/warehouse'
				}).then(res=>{
					console.log(res)
					this.SelecFoltList=res.data.list
				})
			},
			
			//下拉框取值时绑定业务员id
			selectEmployee(){
				this.employeeList.forEach(e=>{
					if(e.employeeName==this.ruleForm.employeeName)
					this.ruleForm.employeeId=e.employeeId
				})
			},
			
			//下拉框查询业务员
			queryEmployee(){
				if (this.employeeList.length > 0)
					return false
				this.axios({
					methods:'get',
					url:'http://localhost:8089/eims/employee',
				}).then(res=>{
					console.log(res)
					this.employeeList=res.data.list
				}).catch(err=>{
					
				})
			},
			
			submitForm(formName) {
				this.ruleForm.baldetailList.forEach(detail=>{
					detail.blitemDocunum=this.ruleForm.blitemDocunum
				})
				const list=this.ruleForm.baldetailList
				for(var i=0;i<list.length;i++){
					if(typeof(list[i].productId)=="undefined"){
						this.$message({
							type: 'warning',
							message: '请选择要盘点的产品'
						})
						return false
					}else if(typeof(list[i].physicalInventory)=="undefined"){
						this.$message({
							type: 'warning',
							message: '请填写盘点数量！'
						})
						return false
					}
				}
				this.$refs[formName].validate((valid) => {
					if (valid) {
						if(this.isAdd){
							this.axios({
								method:'post',
								url:'http://localhost:8089/eims/blitem',
								data:this.ruleForm
							}).then(res=>{
								console.log("新增数据为：")
								console.log(res)
								this.$message({
									type: 'success',
									message: '盘点单数据新增成功！'
								})
								this.$router.push({name:'Blitem'})
							}).catch(err=>{
								
							})
						}else{
							if(this.ruleForm.audited==1){
								this.$message({
									type: 'info',
									message: '已审核的数据无法更改'
								})
								this.show()
								return false
							}
							this.axios({
								method:'put',
								url:'http://localhost:8089/eims/blitem',
								data:this.ruleForm
							}).then(res=>{
								this.$message({
									type: 'success',
									message: '盘点单数据更改成功！'
								})
								this.$router.push({name:'Blitem'})
							}).catch(err=>{
								
							})
						}
					} else {
						return false;
					}
					
				});
			},
			//删除行按钮
			removeRow(index) {
				if (this.ruleForm.baldetailList.length > 1)
					this.ruleForm.baldetailList.splice(index, 1);
			},
			//表格增加行
			addRow() {
				this.ruleForm.baldetailList.push({});
			},

			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			
			show(){
				this.axios({
					method:'get',
					url:'http://localhost:8089/eims/blitem/one',
					params:{
						"id":this.$route.params.blitemId
					}
				}).then(res=>{
					
					this.ruleForm=res.data
					this.ruleForm.baldetailList=res.data.baldetailList
					//console.log("------"+res.data)
					this.multipleSelection=res.data.baldetailList
					if(this.ruleForm.audited==1){
						this.isdisabled=true
					}else{
						this.isdisabled=false
					}
				})
			},
		},
		created() {
			this.ruleForm.blitemId = this.$route.params.blitemId
			this.isAdd = typeof(this.ruleForm.blitemId) == "undefined" || this.ruleForm.blitemId == ""
			if (this.isAdd)
				this.getDocuDate("PDD")
			else {
				this.show()
			}
		}
	}
</script>
<style>
	#Addblitem {
		/* background-color: aliceblue; */
	}

	#Addblitem .el-container {
		height: 100%;
		width: 100%;
	}
	
	/* #Addblitem .underline-input {
		border: 0px;
		outline: none;
		width: 105px;
		padding: 1px 0px;
		border-bottom: 1px solid rgb(204, 204, 204);
	}
	
	#Addblitem .el-footer {
		padding-bottom: 20px;
	}
	
	
	.underline-input:hover {
		border-bottom: 1px solid rgb(35, 134, 238);
	} */

	#Addblitem .el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
	}

	#Addblitem .el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
		height: 100%;

	}


	#Addblitem .el-row {
		margin-bottom: 5px;
	}

	#Addblitem .el-col {
		border-radius: 4px;
	}

	#Addblitem .row-bg {
		padding: 10px 0;
		background-color: #f9fafc;
	}

	#Addblitem .item {
		margin-top: 10px;
		margin-right: 10px;
	}

	/* .el-input__inner{
			width: 200px;
		} */
	#Addblitem .font-style {
		text-align: right;
		vertical-align: middle;
		font-size: 14px;
		color: #606266;
		line-height: 40px;
		-webkit-box-sizing: border-box;
		box-sizing: border-box;
	}
	
	#Addblitem .ReasonClass{
		font-size: 26px;
		color: #ff0000;
	}

	#Addblitem .el-dialog__body {
		padding: 5px;
	}

	#Addblitem .el-table td,
	#Addblitem .el-table th,
	#Addblitem .el-table table{
		padding: 6px 0;
		/* border-color:#606266; */
	}
</style>
