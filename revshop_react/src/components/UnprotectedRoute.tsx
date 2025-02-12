import {Navigate, Outlet} from "react-router-dom";

const UnprotectedRoute = () => {
    const jwt = localStorage.getItem("jwt");
    if (jwt) {
        return <Navigate to="/dashboard" replace />;
    }
    return <Outlet />;
}
export default UnprotectedRoute;