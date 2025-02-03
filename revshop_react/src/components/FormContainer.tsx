import React from 'react';
import { Box, Paper, Typography } from '@mui/material';

interface FormContainerProps {
    title: string;
    children: React.ReactNode;
}

const FormContainer: React.FC<FormContainerProps> = ({ title, children }) => {
    return (
        <Box
            display="flex"
            justifyContent="center"
            alignItems="center"
            minHeight="100vh"
            bgcolor="#f5f5f5"
        >
            <Paper
                elevation={3}
                sx={{
                    padding: 4,
                    width: '100%',
                    maxWidth: 400,
                    textAlign: 'center',
                }}
            >
                <Typography variant="h5" component="h1" gutterBottom>
                    {title}
                </Typography>
                {children}
            </Paper>
        </Box>
    );
};

export default FormContainer;