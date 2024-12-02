import http from "../http-common"; 

class ValuationTeamService {
  getAllValuationTeams(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/valuationTeam/valuationTeams`, searchDTO);
  }

  get(valuationTeamId) {
    return this.getRequest(`/valuationTeam/${valuationTeamId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/valuationTeam?field=${matchData}`, null);
  }

  addValuationTeam(data) {
    return http.post("/valuationTeam/addValuationTeam", data);
  }

  update(data) {
  	return http.post("/valuationTeam/updateValuationTeam", data);
  }
  
  uploadImage(data,valuationTeamId) {
  	return http.postForm("/valuationTeam/uploadImage/"+valuationTeamId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new ValuationTeamService();
