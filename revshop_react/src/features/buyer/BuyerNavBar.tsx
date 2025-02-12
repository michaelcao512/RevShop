import NavBar from "../../components/NavBar.tsx";

const BuyerNavBar = () => {

    // Buyer-specific navigation links
    const buyerNavLinks = [
        { label: 'Products', path: '/buyer/products' },
        { label: 'Cart', path: '/buyer/cart' },
        { label: 'Orders', path: '/buyer/orders' },
        { label: 'Favorites', path: '/buyer/favorites' },
    ];

    return (
        <NavBar
            title="Buyer Dashboard"
            navLinks={buyerNavLinks}
        />
    );
};

export default BuyerNavBar;