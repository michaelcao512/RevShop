import { FC } from "react";
import {User} from "../../types";
import SellerNavBar from "./SellerNavBar.tsx";

const SellerDashboard: FC<{ user: User }> = ({ user }) => {
    return (
        <>
            <SellerNavBar/>
            <div>
                <h1>Welcome, Seller!</h1>
                <p>Email: {user.email}</p>
                <p>User ID: {user.userId}</p>
            </div>
        </>
    );
};

export default SellerDashboard;