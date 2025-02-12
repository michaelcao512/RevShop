export type Error = {
    message: string;
}
export type User = {
    userId: number;
    email: string;
    userType: 'SELLER' | 'BUYER';

}

export type Product = {
    id: number;
    name: string;
}

export type Cart = {
    id: number;
    productName: string;
}

export type Order ={
    id: number;
}

export type Review ={
    id: number;
}

export type Favorite = {
    id: number;
}

export type Notification = {
    id: number;
}

export type Authority = {
    authority: string;
}