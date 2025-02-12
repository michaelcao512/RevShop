import NavBar from "../../components/NavBar.tsx";

const SellerNavBar = () => {

    // Seller-specific navigation links
    const sellerNavLinks = [
        { label: 'Products', path: '/seller/products' },
        { label: "Inventory", path: '/seller/inventory' },
        { label: 'Orders', path: '/seller/orders' },
        { label: 'Notifications', path: '/seller/notifications' },
        { label: 'Reviews', path: '/seller/reviews' },
    ];

    return (
        <NavBar
            title="Seller Dashboard"
            navLinks={sellerNavLinks}
        />
    );
};

export default SellerNavBar;