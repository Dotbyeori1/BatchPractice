<template>
    <div>
        <input class="form-control" type="file" id="formFile" @change="handleFileUpload" accept=".csv" />
        <button @click="submitFile">Upload CSV</button>
    </div>
</template>

<script>
import ApiService from "@/services/ApiService";

export default {
    name: "CsvFileUploadComponent",
    data() {
        return {
            selectedFile: null,
        };
    },
    methods: {
        handleFileUpload(event) {
            this.selectedFile = event.target.files[0];
        },
        async submitFile() {
            if (!this.selectedFile) {
                alert('Please select a file to upload');
                return;
            }

            const formData = new FormData();
            formData.append('file', this.selectedFile);

            try {
                const response = await ApiService.uploadCSVFile(formData);
                alert('File uploaded successfully');
                this.$emit('file-uploaded', response.data);
            } catch (error) {
                console.error('Error uploading file:', error);
                alert('Error uploading file');
            }
        }
    }
};
</script>