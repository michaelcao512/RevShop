import {BrowserRouter, Route, Routes} from "react-router-dom";
import RegistrationPage from "./pages/RegistrationPage.tsx";
import LoginPage from "./pages/LoginPage.tsx";
import {QueryClient, QueryClientProvider} from "react-query";
import axios from "axios";
import UnprotectedRoute from "./components/UnprotectedRoute.tsx";
import ProtectedRoute from "./components/ProtectedRoute.tsx";

function App() {
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${jwt}`;
    } else {
        delete axios.defaults.headers.common['Authorization'];
    }
    axios.defaults.baseURL = 'http://localhost:8080';
    axios.defaults.headers.post["Content-Type"] = "application/json";
    axios.defaults.headers.common["Access-Control-Allow-Origin"] = "*";

    const queryClient = new QueryClient();

    return (
    <>
        <QueryClientProvider client={queryClient}>
            <BrowserRouter>
                <Routes>
                    <Route element={<ProtectedRoute token={jwt} />}>
                        <Route path="/dashboard" element={<h1>Dashboard</h1>} />
                    </Route>
                    <Route element={<UnprotectedRoute token={jwt} />}>
                        <Route path={"/"} element={<LoginPage />} />
                        <Route path="/registration" element={<RegistrationPage />} />
                        <Route path={"/login"} element={<LoginPage />} />
                    </Route>
                </Routes>
            </BrowserRouter>
        </QueryClientProvider>
    </>
  )
}

export default App
