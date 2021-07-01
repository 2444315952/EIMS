<template>
  <div id="PurchaseList">
    <el-row>
      <el-breadcrumb
        separator-class="el-icon-arrow-right"
        style="padding-bottom: 16px"
      >
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item
          ><a href="/PurchaseList">供应商列表</a></el-breadcrumb-item
        >
      </el-breadcrumb>
    </el-row>

    <el-container style="background-color: white; padding-top: 15px">
      <el-header style="height: 30px">
        <el-row>
          <el-col :span="18">
            <el-input
              style="width: 290px; float: left"
              placeholder="搜索单据编号/采购单号/供应商/业务员"
              v-model="searchInput"
            >
              <template #append>
                <el-button
                  icon="el-icon-search"
                  size="small"
                  @click="handleSearch"
                ></el-button>
              </template>
            </el-input>
          </el-col>

          <el-col :span="3">
            <el-button
              v-show="showDeleteButton"
              style="float: right"
              size="medium"
              type="primary"
              @click="handleDelete()"
              >删除</el-button
            >
          </el-col>

          <el-col :span="3">
            <el-button
              style="float: right"
              icon="el-icon-plus"
              size="medium"
              type="primary"
              @click="this.$router.push({ name: 'Supplier' })"
              >新增供应商</el-button
            >
          </el-col>
        </el-row>
      </el-header>

      <el-main style="background-color: white">
        <el-table
          :data="tableData"
          max-height="477"
          style="width: 100%; height: 477px"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection"> </el-table-column>
          <el-table-column label="供应商名" width="160" prop="supplierName">
          </el-table-column>
          <el-table-column label="联系人" width="162" prop="contact">
          </el-table-column>
          <el-table-column label="联系电话" prop="contactNumber">
          </el-table-column>
          <el-table-column label="联系地址" prop="contactAddress">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="130">
            <template #default="scope">
              <el-button
                type="text"
				@click="supplier.dialogVisible=true;supplierLoadData(scope.row.supplierId)"
                >查看</el-button
              >
             			 <el-dialog title="供应商" v-model="supplier.dialogVisible">
							<el-row type="flex" justify="end" style="padding-bottom: 12px;">
								<el-col :span="7.5">
									<el-input v-model="supplier.searchInput" placeholder="请搜索供应商名称" size="small">
										<template #append>
											<el-button @click="supplierLoadData()" icon="el-icon-search" size="mini">
											</el-button>
										</template>
									</el-input>
								</el-col>
							</el-row>

							<el-table :data="supplier.tableData" max-height="286" style="height: 286px;"
								highlight-current-row @current-change="supplierSelectionChange">
								<el-table-column property="supplierName" label="供应商名称"></el-table-column>
								<el-table-column property="contact" label="联系人"></el-table-column>
								<el-table-column property="contactNumber" label="联系电话"></el-table-column>
								<el-table-column property="contactAddress" label="联系地址"></el-table-column>
							</el-table>
							<el-row>
								<el-col :span="24">
									
								</el-col>
							</el-row>

							<template #footer>
								<span class="dialog-footer">
									<el-button @click="supplier.dialogVisible = false" size="medium">取 消</el-button>
									<el-button type="primary" @click="supplierConfirmButton" size="medium">确 定
									</el-button>
								</span>
							</template>
						</el-dialog>
              <el-button
                v-if="tableData[scope.$index].audited == 0"
                @click="squeeze(scope.row.payId)"
                type="text"
                style="color: red"
                >删除</el-button
              >

              <el-button
                v-if="tableData[scope.$index].audited == 0"
                @click="handleAudit(scope.row.payId)"
                type="text"
                >审核</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <el-footer>
        <div class="block" style="float: right">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :page-sizes="[10, 20, 40, 80]"
            :page-size="pageParam.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="tableTotal"
          >
          </el-pagination>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
	import qs from 'qs'
	import moment from 'moment'
import { warn } from '@vue/runtime-core'

	export default {
		name: "PurchaseList",
		data() {
			return {
				tableData: [],
				tableTotal: 0,
				multipleSelection: [],
				pageParam: {
					"pageNum": 1,
					"pageSize": 10
				},
				searchInput: '',
				screenCondition: {

				},
				
                supplier:{
                    dialogVisible:false

                    },
				screenDialogVisible: false,
				showDeleteButton: true,
				queryType: 'all',
				
       			
			}
		},
		computed: {
			searchCondition() {
				return {
					payDocunum: this.searchInput,
					purchDocunum: this.searchInput,
					supplierName : this.searchInput,
					employeeName : this.searchInput
				}
			}
		},
		methods: {
			dateFormat(row, column) {
				var date = row[column.property];
				if (date == undefined) {
					return ''
				};
				return moment(date).format("YYYY-MM-DD HH:mm")
			},
			loadData(){
				this.axios({
					url:"http://localhost:8089/eims/supplier",
					method:'get',
					params:this.pageParam
				}).then((response) =>{
					this.tableData = response.data.list
					this.tableTotal = response.data.total//有几行总记录数
					console.log(response)
				}).catch((error) =>{

				}) 
			},
			ab(){
					this.supplier.dialogVisible=true
				},
	supplierLoadData(id){
			this.axios({
				url:"http://localhost:8089/eims/supplier/table",
				method:'get',
				params: {supplierId:id}
				}).then((response) =>{
					console.log(response.data)
				this.supplier.tableData = response.data.list
				//this.supplier.tableTotal = response.data.total
				console.log(response)
				}).catch((error) =>{

				}) 
			},
			handleSelectionChange(val) {
				console.log(val)
				this.multipleSelection = val

				
			},
			handleSizeChange(val) {
				this.pageParam.pageSize=val
				if(this.queryType == 'all')
					this.loadData()
				else if (this.queryType == 'search')
					this.handleSearch()
				
			},
			handleCurrentChange(val) {
				this.pageParam.pageNum = val

				if (this.queryType == 'all')
					this.loadData()
				else if (this.queryType == 'search')
					this.handleSearch()
				
			},
			
			
			squeeze(val){
				// alert(val)
				
				this.$confirm('此操作将会删除该数据且不能撤回，是否继续？','提示',{
					confirmButtonTest:'确定',
					cancelButtonTest:'取消',
					type:'warning'
				}).then(() => {
					this.axios({
						url:"http://localhost:8089/eims/supplier",
						method:'Delete',
						params:{"id":val}
					}).then(response => {
						this.loadData()
						this.$message({
							type:"success",
							message:'删除成功'
						})
					}).catch(() => {
						this.message({
							type:"info",
							message:'已取消操作'
						})
					})
				})
			},
			handleAudit(val) {
				this.$confirm('此操作将通过审核，是否继续？','提示',{
					confirmButtonTest:'确定',
					cancelButtonTest:'取消',
					type:'warning'
				}).then(() => {
					console.log(val)
					this.axios({
						url:"http://localhost:8089/eims/supplier",
						method:"put",
						data:{
							"payId":val,
							"audited":1
						}
					}).then(response => {
						this.loadData()
						this.$message({
							type:'success',
							message:'审核成功'
							
						})
					}).catch(() => {
						this.$message({
							type:'info',
							message:'已取消操作'
						})
					})
				})
			},
			handleDelete() {
				var isHaveAudited = false
				this.multipleSelection.forEach(m => {
					if (m.audited == 1) {
						isHaveAudited = true
						return false
					}
				})
				if(this.multipleSelection == 0){
					this.$message({
						type: 'error',
						message: '请先选择你要删除的付款单！'
					})
					return false
				}


				if (isHaveAudited) {
					this.$message({
						type: 'info',
						message: '无法删除已审核的数据'
					})
					return false
				}

				this.$confirm('此操作将永久删除文件，是否继续？', '提示', {
					confirmButtonTest: '确定',
					cancelButtonTest: '取消',
					type: 'warning'
				}).then(() => {
					var ids = new Array()
					for (let i = 0; i < this.multipleSelection.length; i++)
						ids.push(this.multipleSelection[i].payId)
						console.log(ids)
					this.axios({
						url: "http://localhost:8089/eims/supplier/batch",
						method: "delete",
						data: ids
					}).then(response => {
						this.loadData()
						console.log(response)
						this.$message({
							type: 'success',
							message: '删除成功'
						});
					}).catch(error => {

					})
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消操作'
					})
				})
			},
			handleSearch() {
				this.queryType = 'search'

				console.log(this.searchInput)
				console.log(this.searchCondition)
				var searchForm = Object.assign(this.searchCondition,this.pageParam)
				
				this.axios({
					url: "http://localhost:8089/eims/supplier/search",
					method: "get",
					params: searchForm
				}).then(response => {
					this.tableData = response.data.list
					this.tableTotal = response.data.total
				}).catch(error => {

				})
			},
			handleScreen() {
				
			},
			toDetail() {
				this.$router.push({
					name:'pi'
				})
			}
		},
		created() {
			this.loadData();
		}
	}
</script>

<style>
#PurchaseList .el-table .cell .el-button {
  padding: 0px;
  min-height: 22px;
  height: 22px;
}
</style>
