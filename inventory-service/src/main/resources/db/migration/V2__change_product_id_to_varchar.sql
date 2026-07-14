ALTER TABLE inventory
MODIFY COLUMN product_id VARCHAR(255) NOT NULL;

ALTER TABLE inventory
ADD UNIQUE INDEX uk_inventory_product_id (product_id);