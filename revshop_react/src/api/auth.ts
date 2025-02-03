import axios from "axios";

const url = "api/auth"

const Auth = {
    login:  (email: string, password: string) => {
            return axios.post(
                `${url}/login`,
                JSON.stringify({ email, password })
            ).then(response => {
                localStorage.setItem('jwt', response.data);
                axios.defaults.headers.common['Authorization'] = `Bearer ${response.data}`;
                return response.data;
            }).catch(error => {
                throw error;
            })
    },
    register:  (email: string, password: string, userType: string) => {
        return axios.post(
            `${url}/register`,
            {
            email: email,
            password: password,
            userType: userType
        }).then(response => {
            return response.data;
        }).catch(error => {
            throw error;
        });

    },

    logout:  () => {
        localStorage.removeItem('jwt');
        delete axios.defaults.headers.common['Authorization'];
    }

}

export default Auth;