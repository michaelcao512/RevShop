import {Link, Outlet} from "react-router-dom";
import {Typography} from "@mui/material";
import {FC} from "react";
import SellerNavBar from "../features/seller/SellerNavBar.tsx";
import BuyerNavBar from "../features/buyer/BuyerNavBar.tsx";

const ProtectedRoute: FC< {userType: string}> = ({userType}) => {
    const jwt = localStorage.getItem("jwt");
    if (!jwt) {
        return (
            <Link to="/login">
                <Typography variant="h2">Please Login</Typography>
            </Link>
        );
    }

    return( <>
        {userType === 'SELLER' ? <SellerNavBar /> : <BuyerNavBar />}
        <Outlet />

    </>);
};

export default ProtectedRoute;