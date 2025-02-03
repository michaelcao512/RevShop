import {Navigate, Outlet} from "react-router-dom";
import React from "react";

const UnprotectedRoute: React.FC<{token: string | null}> = ({token}) => {

    if (token) {
        return <Navigate to="/dashboard" replace />;
    }
    return <Outlet />;
}
export default UnprotectedRoute;