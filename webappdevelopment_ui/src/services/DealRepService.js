import http from "../http-common"; 

class DealRepService {
  getAllDealReps(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/dealRep/dealReps`, searchDTO);
  }

  get(dealRepId) {
    return this.getRequest(`/dealRep/${dealRepId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/dealRep?field=${matchData}`, null);
  }

  addDealRep(data) {
    return http.post("/dealRep/addDealRep", data);
  }

  update(data) {
  	return http.post("/dealRep/updateDealRep", data);
  }
  
  uploadImage(data,dealRepId) {
  	return http.postForm("/dealRep/uploadImage/"+dealRepId, data);
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

export default new DealRepService();
