import axios from "axios";
import {Product} from "../../types.ts";

const url = "api/products";

const Products = {
    getAllProducts: async () => {
            const response = await axios.get(`${url}/products`);
            return response.data;
    },
    getProductById: async (productId: string) => {
            const response = await axios.get(`${url}/products/${productId}`);
            return response.data;

    },
    addProduct: async (product: Product) => {
            const response = await axios.post(`${url}/products`, product);
            return response.data;

    },
    updateProduct: async (productId: string, product: Product) => {
            const response = await axios.patch(`${url}/products/${productId}`, product);
            return response.data;
    },
    deleteProduct: async (productId: string) => {
            const response = await axios.delete(`${url}/products/${productId}`);
            return response.data;
    }

}

export default Products;