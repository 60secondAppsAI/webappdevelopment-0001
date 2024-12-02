<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <customerSubmission-table
            v-if="customerSubmissions && customerSubmissions.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:customerSubmissions="customerSubmissions"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-customer-submissions="getAllCustomerSubmissions"
             >

            </customerSubmission-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CustomerSubmissionTable from "@/components/CustomerSubmissionTable";
import CustomerSubmissionService from "../services/CustomerSubmissionService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CustomerSubmissionTable,
  },
  data() {
    return {
      customerSubmissions: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllCustomerSubmissions(sortBy='customerSubmissionId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CustomerSubmissionService.getAllCustomerSubmissions(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.customerSubmissions.length) {
					this.customerSubmissions = response.data.customerSubmissions;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching customerSubmissions:", error);
        }
        
      } catch (error) {
        console.error("Error fetching customerSubmission details:", error);
      }
    },
  },
  mounted() {
    this.getAllCustomerSubmissions();
  },
  created() {
    this.$root.$on('searchQueryForCustomerSubmissionsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCustomerSubmissions();
    })
  }
};
</script>
<style></style>
