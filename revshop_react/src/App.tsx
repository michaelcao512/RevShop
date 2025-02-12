import { BrowserRouter, Route, Routes } from "react-router-dom";
import RegistrationPage from "./pages/RegistrationPage.tsx";
import LoginPage from "./pages/LoginPage.tsx";
import { QueryClient, QueryClientProvider } from "react-query";
import axios from "axios";
import UnprotectedRoute from "./components/UnprotectedRoute.tsx";
import ProtectedRoute from "./components/ProtectedRoute.tsx";
import DashboardPage from "./pages/DashboardPage.tsx";
import {Products} from "./features/seller/Products.tsx";
import {Inventory} from "./features/seller/Inventory.tsx";
import {Reviews} from "./features/seller/Reviews.tsx";
import {Notifications} from "./features/seller/Notifications.tsx";
import {Orders} from "./features/seller/Orders.tsx";

function App() {

    const jwt = localStorage.getItem("jwt");
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
            <QueryClientProvider client={queryClient}>
                <BrowserRouter>
                    <Routes>
                        <Route path="/dashboard" element={<DashboardPage />} />

                        <Route element={<ProtectedRoute userType="SELLER" />}>
                            <Route path="/seller/products" element={<Products />} />
                            <Route path="/seller/inventory" element={<Inventory />} />
                            <Route path="/seller/orders" element={<Orders />} />
                            <Route path="/seller/notifications" element={<Notifications />} />
                            <Route path="/seller/reviews" element={<Reviews />} />
                        </Route>
                        <Route element={<ProtectedRoute userType="BUYER" />}>
                        </Route>
                        <Route element={<UnprotectedRoute />}>
                            <Route path={"/"} element={<LoginPage />} />
                            <Route path="/registration" element={<RegistrationPage />} />
                            <Route path={"/login"} element={<LoginPage />} />
                        </Route>
                    </Routes>
                </BrowserRouter>
            </QueryClientProvider>
    );
}

export default App;