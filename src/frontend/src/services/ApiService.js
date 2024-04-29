import axios from 'axios';

const API_URL = '/api'; // Spring Boot 서버 주소
export default {
    getMainData() {
        return axios.get(API_URL+"/main");
    },
    getAuthCheckList() {
        return axios.get(API_URL+"/authCheckList")
    },
    uploadCSVFile(formData) {
        return axios.post(API_URL + "/authCheckInsert", formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
    }
};
