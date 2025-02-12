import axios from "axios";

const url = "api/auth"

const Auth = {

    login:  async (email: string, password: string) => {
            const response = await axios.post(
                `${url}/login`,
                JSON.stringify({email, password})
            );
            localStorage.setItem('jwt', response.data.jwt);
            localStorage.setItem('userId', response.data.userId);
            axios.defaults.headers.common['Authorization'] = `Bearer ${response.data.jwt}`;
            return response.data;
    },
    register:  async (email: string, password: string, userType: string) => {
            const response = await axios.post(
                `${url}/register`,
                {
                    email: email,
                    password: password,
                    userType: userType
                });
            return response.data;
    },

    logout:  () => {
        localStorage.removeItem('jwt');
        localStorage.removeItem('userId');
        delete axios.defaults.headers.common['Authorization'];
    }

}

export default Auth;