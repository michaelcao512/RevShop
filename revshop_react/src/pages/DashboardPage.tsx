import { useQuery } from "react-query";
import axios from "axios";
import { Typography } from "@mui/material";
import SellerDashboard from "../features/seller/SellerDashboard.tsx";
import {BuyerDashboard} from "../features/buyer/BuyerDashboard.tsx";
import {User} from "../types.ts";



const DashboardPage = () => {
    const userId = localStorage.getItem('userId');

    const { data: user, isLoading, isError, error } = useQuery<User, Error>(
        ['user', userId],
        async () => {
            const response = await axios.get(`/api/users/${userId}`);
            return response.data;
        },
        {
            enabled: !!userId,
            refetchOnMount: "always",
            refetchOnWindowFocus: "always",
        }
    );

    if (isLoading) {
        return <Typography>Loading...</Typography>;
    }

    if (isError) {
        return <Typography>Error: {error.message}</Typography>;
    }
    if (!user) {
        return <Typography>User not found</Typography>;
    }
    return (
        <div>
            {user.userType === 'SELLER' ? (
                <SellerDashboard user={user} />
            ) : (
                <BuyerDashboard user={user} />
            )}
        </div>
    );
};

export default DashboardPage;