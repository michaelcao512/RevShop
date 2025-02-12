import { useForm, SubmitHandler, Controller } from 'react-hook-form';
import { TextField, Button, Typography } from '@mui/material';
import FormContainer from '../components/FormContainer';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import {useMutation} from "react-query";
import Auth from "../api/auth.ts";
import {useLocation, useNavigate} from "react-router-dom";
import {FC} from "react";

type LoginFormInputs = {
    email: string;
    password: string;
};

const defaultValues: LoginFormInputs = {
    email: '',
    password: '',
};

const schema = z
    .object({
        email: z.string().email('Invalid email address'),
        password: z.string().min(6, 'Password must be at least 6 characters'),
    });


const LoginPage: FC = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const successMessage = location.state?.message;


    const {
        control,
        handleSubmit,
        formState: { errors },
    } = useForm<LoginFormInputs>({
        resolver: zodResolver(schema),
        defaultValues: defaultValues,
    });

    const mutation = useMutation({
        mutationFn: (formData: LoginFormInputs) => {
            return Auth.login(formData.email, formData.password);
        },
        onSuccess: () => {
            navigate('/dashboard', { state: { message: 'Logged in successfully' }, replace: true });
        }
    });

    const onSubmit: SubmitHandler<LoginFormInputs> = (data) => {
        mutation.mutate(data);
    };

    return (
        <>
        {mutation.isError && <div>Failed to login</div>}
            {mutation.isLoading && <div>Logging in...</div>}
            {mutation.isSuccess && <div>Logged in</div>}
            {successMessage && <Typography variant="body1" color="success" align="center">{successMessage}</Typography>}

        <FormContainer title="Log In">
            <form onSubmit={handleSubmit(onSubmit)}>
                <Controller
                    name="email"
                    control={control}
                    render={({ field }) => (
                        <TextField
                            fullWidth
                            label="Email"
                            variant="outlined"
                            margin="normal"
                            error={!!errors.email}
                            helperText={errors.email?.message}
                            onBlur={field.onBlur}
                            onChange={field.onChange}
                            value={field.value}
                        />
                    )}
                />

                <Controller
                    name="password"
                    control={control}
                    render={({ field }) => (
                        <TextField
                            fullWidth
                            label="Password"
                            variant="outlined"
                            margin="normal"
                            type="password"
                            error={!!errors.password}
                            helperText={errors.password?.message}
                            onBlur={field.onBlur}
                            onChange={field.onChange}
                            value={field.value}
                        />
                    )}
                />

                <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    color="primary"
                    sx={{ mt: 3, mb: 2 }}
                >
                    Log in
                </Button>

                <Typography variant="body2" color="textSecondary" align="center">
                    Don't have an account? <a href="/registration">Register</a>
                </Typography>
            </form>
        </FormContainer>
        </>
    );
}

export default LoginPage;