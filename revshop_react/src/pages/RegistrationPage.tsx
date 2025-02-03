import {useForm, SubmitHandler, Controller, useWatch} from 'react-hook-form';
import {
    TextField,
    Button,
    Typography,
    FormControl,
    RadioGroup,
    FormControlLabel, Radio
} from '@mui/material';
import FormContainer from '../components/FormContainer';
import {useEffect} from 'react';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import {useMutation} from "react-query";
import Auth from "../api/auth.ts";
import {useNavigate} from "react-router-dom";
import {AxiosError} from "axios";

type RegistrationFormInputs = {
    email: string;
    password: string;
    confirmPassword: string;
    userType: string;
};

const defaultValues: RegistrationFormInputs = {
    email: '',
    password: '',
    confirmPassword: '',
    userType: 'BUYER'
};

const schema = z
    .object({
        email: z.string().email('Invalid email address'),
        password: z.string().min(6, 'Password must be at least 6 characters'),
        confirmPassword: z.string(),
        userType: z.enum(['BUYER', 'SELLER'])
    })
    .refine(
        (data) => data.password === data.confirmPassword,
        {
            message: 'Passwords do not match',
            path: ['confirmPassword'],
        }

    );

function RegistrationPage() {
    const navigate = useNavigate();
    const {
        control,
        handleSubmit,
        formState: { errors },
        trigger,
    } = useForm<RegistrationFormInputs>({
        resolver: zodResolver(schema),
        defaultValues: defaultValues,
    });

    const mutation = useMutation({
        mutationFn: (formData: RegistrationFormInputs) =>
            Auth.register(formData.email, formData.password, formData.userType),
        onSuccess: () => {
            navigate('/login', { state: { message: 'Registration successful! Please login.' } });
        },
    })

    const onSubmit: SubmitHandler<RegistrationFormInputs> = (formData) => {
        mutation.mutate(formData);
    }

    const passwords = useWatch({
        control,
        defaultValue: {
            password: '',
            confirmPassword: '',
        }});
    useEffect(() => {
        if (passwords.password && passwords.confirmPassword) {
            trigger('confirmPassword').then();
        }
    }, [passwords.password, passwords.confirmPassword, trigger]);

    return (
        <>
            {mutation.isError && mutation.error instanceof AxiosError && <Typography variant="body1" color="error" align="center">Error: {mutation.error?.response?.data}</Typography>}
            {mutation.isSuccess && <Typography variant="body1" color="success" align="center">Registration successful!</Typography>}
            {mutation.isLoading && <Typography variant="body1" align="center">Loading...</Typography>}
        <FormContainer title="Register">
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

                <Controller
                    name="confirmPassword"
                    control={control}
                    render={({ field }) => (
                        <TextField
                            fullWidth
                            label="Confirm Password"
                            variant="outlined"
                            margin="normal"
                            type="password"
                            error={!!errors.confirmPassword}
                            helperText={errors.confirmPassword?.message}
                            onBlur={field.onBlur}
                            onChange={field.onChange}
                            value={field.value}
                        />
                    )}
                />

                <Controller
                    name="userType"
                    control={control}
                    render={
                        ({ field }) => (
                            <FormControl component="fieldset" sx={{ mt: 2, mb: 2 }}>
                                <RadioGroup
                                    onChange={field.onChange}
                                    value={field.value}
                                    sx={{ display: 'flex', flexDirection: 'row' }}
                                >
                                    <FormControlLabel
                                        value="BUYER"
                                        control={<Radio  />}
                                        label="Buyer"
                                        sx={{ mr: 2 }}
                                    />
                                    <FormControlLabel
                                        value="SELLER"
                                        control={<Radio  />}
                                        label="Seller"
                                    />
                                </RadioGroup>
                            </FormControl>
                        )
                    }
                />

                <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    color="primary"
                    sx={{ mt: 3, mb: 2 }}
                >
                    Register
                </Button>

                <Typography variant="body2" color="textSecondary" align="center">
                    Already have an account? <a href="/login">Login here</a>
                </Typography>
            </form>
        </FormContainer>
        </>
    );
}

export default RegistrationPage;