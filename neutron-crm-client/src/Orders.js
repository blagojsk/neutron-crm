import React, {useEffect, useState} from 'react';
import axios from 'axios';

const Orders = () => {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/orders')
            .then(response => {
                setOrders(response.data);
            })
            .catch(error => {
                console.error('Error fetching orders:', error);
            });
    }, []);

    const handleFollowUp = (orderId) => {
        fetch(`http://localhost:8080/api/orders/follow-up?orderId=${orderId}`, {
            method: 'POST'
        })
            .then(response => {
                if (response.ok) {
                    console.log('Order added to follow-up.');
                }
            });
    };

    const toggleOrderLines = (index) => {
        const updatedOrders = [...orders];
        updatedOrders[index].showLines = !updatedOrders[index].showLines;
        setOrders(updatedOrders);
    };

    return (
        <div>
            <h2>Orders</h2>
            <table className="table">
                <thead>
                <tr>
                    <th>Customer Name</th>
                    <th>Date</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                {orders.map((order, index) => (
                    <React.Fragment key={order.id}>
                        <tr>
                            <td>{order.customerName}</td>
                            <td>{order.date}</td>
                            <td>
                                <button className="btn btn-primary" onClick={() => handleFollowUp(order.id)}>Add to
                                    Follow-Up
                                </button>
                                <button className="btn btn-secondary ml-2" onClick={() => toggleOrderLines(index)}>
                                    {order.showLines ? 'Hide Lines' : 'Show Lines'}
                                </button>
                            </td>
                        </tr>
                        {order.showLines &&
                            <tr>
                                <td colSpan="3">
                                    <table className="table">
                                        <thead>
                                        <tr>
                                            <th>Product Name</th>
                                            <th>Quantity</th>
                                            <th>Total Price</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {order.orderLines.map(line => (
                                            <tr key={line.id}>
                                                <td>{line.productName}</td>
                                                <td>{line.quantity}</td>
                                                <td>{line.price.toFixed(2)}</td>
                                            </tr>
                                        ))}
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        }
                    </React.Fragment>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Orders;
