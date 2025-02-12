import {FC} from "react";
import {User} from "../../types.ts";
import {Typography} from "@mui/material";
import BuyerNavBar from "./BuyerNavBar.tsx";

export const BuyerDashboard: FC<{user: User }> = ({ user } ) => {
    return (
        <>
            <BuyerNavBar />
            <Typography variant="h1">Welcome, Buyer!</Typography>
            {user.email}
        </>
    );
};