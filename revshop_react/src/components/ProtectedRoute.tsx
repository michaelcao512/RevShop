import {Link, Outlet} from "react-router-dom";
import {FC} from "react";
import {Typography} from "@mui/material";

const ProtectedRoute: FC<{token: string | null}> = ({token}) => {
    if (token == null) {
        return (
            <>
                    <Link to="/login">
                        <Typography variant="h2">
                            Please Login
                        </Typography>
                    </Link>

            </>
        );
    }
    return <Outlet />;
}

export default ProtectedRoute;