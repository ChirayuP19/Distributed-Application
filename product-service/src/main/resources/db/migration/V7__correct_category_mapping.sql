-- 🔌 Electronics (PRIORITY FIRST)
UPDATE products
SET category_id = (SELECT id FROM categories WHERE name = 'Electronics')
WHERE category_id IS NULL
  AND (
    name ILIKE '%smartphone%' OR
    name ILIKE '%laptop%' OR
    name ILIKE '%camera%' OR
    name ILIKE '%speaker%' OR
    name ILIKE '%headphones%' OR
    name ILIKE '%tablet%' OR
    name ILIKE '%watch%'
    );

-- 👟 Fashion (ONLY shoes)
UPDATE products
SET category_id = (SELECT id FROM categories WHERE name = 'Fashion')
WHERE category_id IS NULL
  AND (
    name ILIKE '%shoes%'
    );

-- 🏠 Home (not really in your data but safe)
UPDATE products
SET category_id = (SELECT id FROM categories WHERE name = 'Home')
WHERE category_id IS NULL
  AND (
    name ILIKE '%table%' OR
    name ILIKE '%chair%' OR
    name ILIKE '%sofa%'
    );

-- 📚 Books (none in your data but safe)
UPDATE products
SET category_id = (SELECT id FROM categories WHERE name = 'Books')
WHERE category_id IS NULL
  AND (
    name ILIKE '%book%'
    );

-- 🔥 FINAL FALLBACK (everything else → Electronics)
UPDATE products
SET category_id = (SELECT id FROM categories WHERE name = 'Electronics')
WHERE category_id IS NULL;