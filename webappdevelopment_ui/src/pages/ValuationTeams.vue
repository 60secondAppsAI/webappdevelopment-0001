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
            <valuationTeam-table
            v-if="valuationTeams && valuationTeams.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:valuationTeams="valuationTeams"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-valuation-teams="getAllValuationTeams"
             >

            </valuationTeam-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ValuationTeamTable from "@/components/ValuationTeamTable";
import ValuationTeamService from "../services/ValuationTeamService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ValuationTeamTable,
  },
  data() {
    return {
      valuationTeams: [],
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
    async getAllValuationTeams(sortBy='valuationTeamId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ValuationTeamService.getAllValuationTeams(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.valuationTeams.length) {
					this.valuationTeams = response.data.valuationTeams;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching valuationTeams:", error);
        }
        
      } catch (error) {
        console.error("Error fetching valuationTeam details:", error);
      }
    },
  },
  mounted() {
    this.getAllValuationTeams();
  },
  created() {
    this.$root.$on('searchQueryForValuationTeamsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllValuationTeams();
    })
  }
};
</script>
<style></style>
