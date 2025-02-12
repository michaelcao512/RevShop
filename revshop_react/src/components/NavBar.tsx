import React from 'react';
import { AppBar, Toolbar, Typography, Button, IconButton, Menu, MenuItem, Box } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import LogoutIcon from '@mui/icons-material/Logout';
import { useNavigate } from 'react-router-dom';
import Auth from "../api/auth.ts";

interface NavBarProps {
    title: string;
    navLinks: { label: string; path: string }[];
}

const NavBar = ({ title, navLinks}: NavBarProps) => {
    const navigate = useNavigate();
    const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);

    const handleMenuOpen = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorEl(event.currentTarget);
    };

    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    const onLogout = () => {
        Auth.logout();
        navigate("/login");
    };

    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    {title}
                </Typography>
                <Box sx={{ display: { xs: 'none', md: 'flex' } }}>
                    <Button color="inherit" onClick={() => navigate("/dashboard")}>
                        Dashboard
                    </Button>

                    {navLinks.map((link) => (
                        <Button
                            key={link.path}
                            color="inherit"
                            onClick={() => navigate(link.path)}
                        >
                            {link.label}
                        </Button>
                    ))}
                </Box>

                <Box sx={{ display: { xs: 'flex', md: 'none' } }}>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        onClick={handleMenuOpen}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Menu
                        anchorEl={anchorEl}
                        open={Boolean(anchorEl)}
                        onClose={handleMenuClose}
                    >
                        <MenuItem
                            onClick={() => {
                                navigate("/dashboard");
                                handleMenuClose();
                            }}
                        >
                            Dashboard
                        </MenuItem>

                        {navLinks.map((link) => (
                            <MenuItem
                                key={link.path}
                                onClick={() => {
                                    navigate(link.path);
                                    handleMenuClose();
                                }}
                            >
                                {link.label}
                            </MenuItem>
                        ))}
                    </Menu>
                </Box>

                <Button
                    color="inherit"
                    startIcon={<LogoutIcon />}
                    onClick={onLogout}
                >
                    Logout
                </Button>
            </Toolbar>
        </AppBar>
    );
};

export default NavBar;