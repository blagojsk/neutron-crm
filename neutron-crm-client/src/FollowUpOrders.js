import React, {useEffect, useState} from 'react';

const FollowUpOrders = () => {
    const [followUpData, setFollowUpData] = useState({
        orders: [],
        totalQuantity: 0,
        totalPrice: 0
    });

    useEffect(() => {
        fetch('http://localhost:8080/api/orders/follow-up')
            .then(response => response.json())
            .then(data => {
                setFollowUpData(data);
            });
    }, []);

    return (
        <div>
            <h3>Follow-up Orders</h3>
            <table className="table">
                <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Date</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                {followUpData.orders.map(order => (
                    <tr key={order.id}>
                        <td>{order.productName}</td>
                        <td>{order.date}</td>
                        <td>{order.quantity}</td>
                        <td>{order.price}</td>
                    </tr>
                ))}
                </tbody>
            </table>
            <div>
                <strong>Grand Total:</strong> {followUpData.totalQuantity} items, ${followUpData.totalPrice}
            </div>
        </div>
    );
};

export default FollowUpOrders;
